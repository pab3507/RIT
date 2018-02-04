from datetime import datetime
from django.http import HttpResponse
from django import forms
from django.contrib import auth
from django.contrib.auth import login, logout
from django.contrib.auth.models import User
from django.core import serializers
from django.db.models import Q
from django.shortcuts import render, redirect
from django.utils import timezone
from .forms import AppointmentForm, EditProfileForm, PersonLoginForm, \
    RegisterForm, UploadPatientForm, UpdatePatientForm, UpdateUsertypeForm
from .models import Activity, Appointment, Person
import json
import re
import time

'''
### Authors: TeamD MoisesIsLateAgain
### Members: Bryan Camp, Yancarlos Diaz, Tyler Collins, Michael Hopkins, and Moisés Lora Pérez
### File Description: 
### Views.py is the file that will called html files for displaying. Functionality on these pages are included
### that will edit models.
'''

# Directs user to either /login or /dashboard depending on their logged-in status
def index(request):
    if Person.objects.all().count() == 0:
        createData()
    if request.user.is_authenticated():
        return redirect('/dashboard/')
    else:
        return redirect('/login/')


# The main screen that displays calendar and appointments
def dashboard(request):
    if not request.user.is_authenticated():
        return redirect('/login/')

    if request.user.person.usertype == 'admin':
        return redirect('/adminpanel/')
	
    user = request.user.person
    listOptions = Person.objects.filter(preferred_hospital=user.preferred_hospital).exclude(usertype='nurse')
    appointments = []
    if user.usertype == 'patient':
        appointments = Appointment.objects.filter(p_username=user.username)
    elif user.usertype == 'doctor':
        appointments = Appointment.objects.filter(doctor=user.last_name)
    elif user.usertype == 'nurse':
        appointments = Appointment.objects.filter()

    return render(request, 'dashboard.html', {'createForm': AppointmentForm(prefix="create"),
                                              'deleteForm': AppointmentForm(prefix="delete"),
                                              'appointments': appointments, 'user': user})


#login screen that prompts user for their username and password
def userLogin(request):
    if Person.objects.all().count() == 0:
        createData()
    if request.user.is_authenticated():
        return redirect('/dashboard/')

    if request.method == 'POST':
        form = PersonLoginForm(request.POST)
        user = auth.authenticate(username=request.POST.get('username'), password=request.POST.get('password'))
        if user is not None:
            if user.is_active:
                login(request, user)
                activity = Activity(data=user.username + " logged in",
                                    date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))
                activity.save()
                return redirect('/')
        else:
            return render(request, 'login.html', {'form': PersonLoginForm, 'errors': form.errors})

    return render(request, 'login.html', {'form': PersonLoginForm})

#User logout.
def userLogout(request):
    logout(request)

    return redirect('/')

#View for userRegistration
def userRegister(request):
    global form
    if Person.objects.all().count() == 0:
        createData()
    if request.user.is_authenticated():
        return redirect('/dashboard/')

    if request.method == 'POST':
        form = RegisterForm(request.POST)
        if form.is_valid():
            cleanData = form.cleaned_data #Cleandata functions ensure the forms are filled correctly.
            user = User.objects.create_user(username=cleanData['username'], email=cleanData['email'],
                                            password=cleanData['password'], first_name=cleanData['first_name'].title(),
                                            last_name=cleanData['last_name'].title())
            person = Person(person=user, phone=re.sub("[^0-9]", "", cleanData['phone']),
                            birthdate=cleanData['birthdate'], first_name=cleanData['first_name'].title(),
                            last_name=cleanData['last_name'].title(), email=cleanData['email'],
                            username=cleanData['username'], usertype='patient', sex=cleanData['sex'],
                            preferred_hospital=cleanData['preferred_hospital'], primary_physician='none',
                            last_physical=cleanData['last_physical'])

            primphys = Person.objects.filter(usertype='doctor', preferred_hospital=person.preferred_hospital)[0]
            person.primary_physician = "Dr. " + primphys.last_name
            user.save()
            user = auth.authenticate(username=request.POST.get('username'), password=request.POST.get('password'))
            person.save()

            activity = Activity(data=user.username + " registered",
                                    date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))
            activity.save()
            login(request, user)
            activity = Activity(data=user.username + " logged in",
                                date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))
            activity.save()

            return redirect('/')
        else:

            return render(request, 'register.html', {'form': RegisterForm, 'errors': form.errors})

    return render(request, 'register.html', {'form': RegisterForm})


def profileView(request):
    if not request.user.is_authenticated():
        return redirect('/login/')

    user = request.user.person

    return render(request, 'profile.html', {'user': user})

#View for editing user profile
def profileEdit(request):
    if not request.user.is_authenticated():
        return redirect('/login/')

    person = request.user.person
    if request.method == 'POST':
        if not Person.objects.filter(email=request.POST.get('email')).count() > 0 \
                or request.POST.get('email') == request.user.person.email:

            form = EditProfileForm(request.POST)

            if form.is_valid():
                cleanData = form.cleaned_data #Cleandata functions ensure properly filled fields.

                person.first_name = cleanData['first_name'].title()
                person.last_name = cleanData['last_name'].title()
                person.email = cleanData['email'].replace('>', '').replace('<', '')
                person.birthdate = cleanData['birthdate'].replace('>', '').replace('<', '')
                person.sex = cleanData['sex'].replace('>', '').replace('<', '')
                person.phone = cleanData['phone'].replace('(', '').replace(')', '').replace('-', '').replace(' ','')
                #person.health_history = cleanData['health_history']
                person.last_physical = cleanData['last_physical']
                person.save()
                activity = Activity(data=person.username + " edited their profile",
                                    date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))
                activity.save()

                return redirect('/profile/')
            else:
                return redirect('/editprofile/',
                                {'form': EditProfileForm, 'errors' : form.errors})

    return render(request, 'editprofile.html',
                  {'form': EditProfileForm, 'user': person})


#View for making an appointment, as of this comment there is something broken.
def createAppointment(request):
    if not request.user.is_authenticated():
        return redirect('/login/')

    if request.method == 'POST':
        form = AppointmentForm(request.POST, prefix="create")
        form.is_valid()
        user = request.user.person
        cleanData = form.cleaned_data
        if not 'description' in cleanData:
            return redirect('/dashboard/')
        atime = cleanData['time']
        datetime = cleanData['date'] + "T" + atime

        if int(atime[:2]) < 8 or int(atime[:2]) >= 17:
            return redirect('/dashboard/')

        aptcompare = None
        if user.usertype == "doctor":
            aptcompare = Appointment.objects.filter(doctor=user.last_name)
        else:
            aptcompare = Appointment.objects.filter(doctor=cleanData['doctor'].replace("Dr. ",""))

        for item in aptcompare:
            if (str(item.date)[:16]) == (datetime.replace("T"," ")):
                return redirect('/dashboard/')

        today = time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S")
        notification = Notification(type='appointment', date=today, user=user.username, data=cleanData['date'])

        activity = None
        appointment = None
        if user.usertype == "patient":
            notification.doctor = cleanData['doctor'].replace("Dr. ","")
            notification.data2 = user.first_name + " " + user.last_name + " (" + user.username + ")"
            appointment = Appointment(doctor=cleanData['doctor'].replace('<', '').replace("Dr. ",""), date=datetime,
                                      description=cleanData['description'].replace('<', '').replace('>', ''),
                                      p_username=user.username, p_first_name=user.first_name,
                                      p_last_name=user.last_name, hospital=user.preferred_hospital)
            activity = Activity(data=user.username + " created an appointment with " + cleanData['doctor'],
                                date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))

        elif user.usertype == "doctor":
            notification.doctor = user.last_name
            notification.data2 = cleanData['p_first_name']
            splitString = cleanData['p_first_name'].replace("(","").replace(")","").split()
            appointment = Appointment(doctor=user.last_name, date=datetime,
                                      description=cleanData['description'].replace('<', '').replace('>', ''),
                                      p_username=splitString[2], p_first_name=splitString[0],
                                      p_last_name=splitString[1], hospital=Person.objects.filter(username=splitString[2])[0].preferred_hospital)
            activity = Activity(data=user.username + " created an appointment with " + splitString[2],
                                date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))
        else:
            notification.doctor = cleanData['doctor']
            notification.data2 = cleanData['p_first_name']
            splitString = cleanData['p_first_name'].replace("(","").replace(")","").split()
            appointment = Appointment(doctor=cleanData['doctor'], date=datetime,
                                      description=cleanData['description'].replace('<', '').replace('>', ''),
                                      p_username=splitString[2], p_first_name=splitString[0],
                                      p_last_name=splitString[1], hospital=Person.objects.filter(username=splitString[2])[0].preferred_hospital)
            activity = Activity(data=user.username + " created an appointment for " + splitString[2] + " to meet with "
                                     + cleanData['doctor'],
                                date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))
        notification.save()
        activity.save()
        appointment.save()

    return redirect('/dashboard/')


def deleteAppointment(request, id):
    if not request.user.is_authenticated() or \
                    request.user.person.usertype == 'nurse':
        return redirect('/login/')

    if request.method == 'POST':
        form = AppointmentForm(request.POST, prefix="delete")
        Appointment.objects.filter(pk=id).delete()
        activity = Activity(data=request.user.person.username + " deleted an appointment",
                            date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))
        activity.save()

    return redirect(request.META['HTTP_REFERER'].replace("http://localhost:8000",""))

#View for editing appointments.
def editAppointment(request, id):
    print("")
    if request.method == 'POST':
        user = request.user.person
        form = AppointmentForm(request.POST, prefix="delete")
        form.is_valid()
        cleanData = form.cleaned_data

        if not 'description' in cleanData:
            return redirect('/dashboard/')

        atime = cleanData['time']
        datetime = cleanData['date'] + "T" + atime
        print(atime)
        print(datetime)
        if int(atime[:2]) < 8 or int(atime[:2]) >= 17:
            print("time redirect")
            return redirect(request.META['HTTP_REFERER'].replace("http://localhost:8000",""))

        aptcompare = None
        if user.usertype == "doctor":
            aptcompare = Appointment.objects.filter(doctor=user.last_name, date=datetime.replace("T"," ")).exclude(pk=id)
        else:
            aptcompare = Appointment.objects.filter(doctor=cleanData['doctor'].replace("Dr. ",""), #Change doctor to Dr. for better viewing
                                                    date=datetime.replace("T"," ")).exclude(pk=id)

        if len(aptcompare) >= 1:
            return redirect(request.META['HTTP_REFERER'].replace("http://localhost:8000",""))

        apt = Appointment.objects.get(pk=id)
        apt.doctor = cleanData['doctor'].replace("Dr. ","")
        apt.date = datetime
        apt.description = cleanData['description']
        apt.save()
        activity = Activity(data=user.username + " edited an appointment",
                            date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))
        activity.save()

    return redirect(request.META['HTTP_REFERER'].replace("http://localhost:8000",""))


#Creates a text file of information in patient's profile
#Allows doctors to see a list of all users in the system, attempt at increased doctor functionality.
#Left in because it might break code. Can be implemented in the future.
def exportUser(request, id):

    export_data = str(Person.objects.filter(pk=id))

    response = HttpResponse(export_data, content_type='application/txt')
    response['Content-Disposition'] = 'attachment; filename=' + request.user.person.user_export_fileName() + '.txt'

    # make sure the function ends with this.
    return redirect('/userlist/')

#Appointment view
#View changes depending on the usertype
def appointments(request):
    if not request.user.is_authenticated():
        return redirect('/login/')

    user = request.user.person
    appointments = []
    listOptions = Person.objects.filter().exclude(usertype='nurse') #Nurses cannot see appointments
    if user.usertype == 'patient':
        appointments = Appointment.objects.filter(p_username=user.username)
    elif user.usertype == 'doctor':
        appointments = Appointment.objects.filter(doctor=user.last_name)
    elif user.usertype == 'nurse':
        appointments = Appointment.objects.filter()

    return render(request, 'appointments.html', {'user': user, 'appointments': appointments,
                                                 'options': listOptions, 'form': AppointmentForm(prefix="delete")})

#Allows doctors to see a list of all users in the system, attempt at increased doctor functionality.
#Left in because it might break code. Can be implemented in the future.
def patientlist(request):
    if not request.user.is_authenticated() or \
                    request.user.person.usertype == 'patient':
        return redirect('/login/')

    user = request.user.person
    patients = Person.objects.filter(usertype='patient')
    prescriptions = Prescription.objects.filter()
    doctors = Person.objects.filter(usertype='doctor')
    admitted = Person.objects.filter(usertype='patient', status='Admitted').count()

    return render(request, 'patientlist.html', {'user': user, 'patients': patients, 
                                                'prescriptions': prescriptions, 'form': UpdatePatientForm,
                                                'doctors': doctors, 'admitted': admitted})

												
#Attempt and building another functionality for the admin, left in because the code is working
#removing it might break it.										
def updatepatient(request, id):

    form = UpdatePatientForm(request.POST)
    if form.is_valid():
        cleanData = form.cleaned_data
        patient = Person.objects.filter(pk=id)[0]
        patient.primary_physician = cleanData['u_primary_physician']
        dr = Person.objects.filter(usertype='doctor', last_name=cleanData['u_primary_physician'].replace("Dr. ",""))[0]
        patient.last_physical = cleanData['u_last_physical']
        patient.health_history = cleanData['u_health_history']
        patient.preferred_hospital = cleanData['u_preferred_hospital']

        if not patient.preferred_hospital == dr.preferred_hospital:
            patient.preferred_hospital = dr.preferred_hospital

        patient.save()

    return redirect('/userlist/')

#View for the admin when they access the main site. Will display the activity log on this page.
def adminpanel(request):
    if not request.user.is_authenticated() or request.user.person.usertype != 'admin':
        return redirect('/login/')
    log = Activity.objects.filter().order_by('-pk')
    return render(request, 'adminpanel.html', {'user': request.user.person, 'log': log})

#Attempt and building another functionality for the admin, left in because the code is working
#removing it might break it.
def updateusertype(request, id):
    if not request.user.is_authenticated() or request.user.person.usertype != 'admin':
        return redirect('/login/')

    
    user = Person.objects.filter(pk=id)[0]

    user.usertype = request.POST.get('usertype').lower()

    user.save()
    activity = Activity(data=request.user.person.username + " changed the usertype of " + user.username + " to "
                             + request.POST.get('usertype').lower(),
                            date=time.strftime("%Y-%m-%d") + "T" + time.strftime("%H:%M:%S"))
    activity.save()

    return redirect('/permissions/')


# When database is wiped, create 2 doctor accounts and 2 nurse accounts. Also 1 Admin user.
# This function is called when the index page is accessed and if there is no current database.
def createData():

    doctors = ['Floppy', 'Yancarlos']
    for i in range(1, 3):
        doctor_username = ("doctor" + str(i))
        user = User.objects.create_user(username=doctor_username, email=(doctors[i - 1].lower() + "@health.net"),
                                        password="password", first_name=doctors[i - 1], last_name=doctors[i - 1])
        user = auth.authenticate(username=doctor_username, password="password")
        user.save()
        person = Person(username=doctor_username, email=(doctors[i - 1].lower() + "@health.net"),
                        password="password", first_name="Person", last_name=doctors[i - 1],
                        person=user, phone=("585000000" + str(i)), birthdate='1973-11-04', usertype='doctor',
                        preferred_hospital='Rochester Hospital', sex='Male', primary_physician='none',
                        last_physical='2014-11-11', status='none')
        if i > 3:
            person.preferred_hospital = 'Rochester Hospital'
        person.save()
        print("Doctor #" + str(i) + " created!")

    nurses = ['Ruth', 'Moises']
    for i in range(1, 3):
        nurse_username = ("nurse" + str(i))
        user = User.objects.create_user(username=nurse_username, email=(nurses[i - 1].lower() + "@health.net"),
                                        password="password", first_name=nurses[i - 1], last_name=nurses[i - 1])
        user.save()
        user = auth.authenticate(username=nurse_username, password="password")
        person = Person(username=nurse_username, email=(nurses[i - 1].lower() + "@health.net"),
                        password="password", first_name="Person", last_name=nurses[i - 1],
                        person=user, phone=("585000001" + str(i)), birthdate='1973-11-04', usertype='nurse',
                        preferred_hospital='Rochester Hospital', sex='Female', primary_physician='none',
                        last_physical='2014-11-11',status='none')
        if i > 4:
            person.preferred_hospital='Rochester Hospital'
        person.save()
        print("Nurse #" + str(i) + " created!")

    user = User.objects.create_user(username='admin', email='admin@health.net', password='admin', first_name='admin', last_name='none')
    user = auth.authenticate(username='admin', password='admin')
    user.save()
    person = Person(username='admin', password='admin', email='admin@health.net', first_name="Admin", last_name='none',
                    person=user, phone='0000000000', birthdate='1973-11-04', usertype='admin', preferred_hospital='none',
                    sex='none', primary_physician='none', last_physical='none', status='active')
    person.save()

    return redirect('/login/')



