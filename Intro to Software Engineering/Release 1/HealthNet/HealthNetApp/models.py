from django.db import models
from django.contrib.auth.models import User
from django.contrib import admin
import re

'''
### Authors: TeamD MoisesIsLateAgain
### Members: Bryan Camp, Yancarlos Diaz, Tyler Collins, Michael Hopkins, and Moisés Lora Pérez
### File Description: 
### Base file of models, these allow for the database to store specific field that we need.
### django.contrib.auth.models is used for verification purposes and other security.
'''


#Model for our person class, this needs to be linked to a user for correct db storage
class Person(models.Model):
    person = models.OneToOneField(User)
    first_name = models.CharField(max_length=25)
    last_name = models.CharField(max_length=25)
    username = models.CharField(max_length=25)
    password = models.CharField(max_length=32)
    birthdate = models.DateField()
    email = models.EmailField(max_length=48)
    phone = models.CharField(max_length=15, default=0)
    sex = models.CharField(max_length=15)
    preferred_hospital = models.CharField(max_length=50)
    usertype = models.CharField(max_length=8, default='patient')	#defaulted to patient to ensure a usertype is selected
    primary_physician = models.CharField(max_length=50)
    last_physical = models.CharField(max_length=50)
    status = models.CharField(max_length=10)
	
	#ugly to string function for basic functionality
    def __str__(self):
        str = self.username                      # Username 0
        str += "," + self.first_name            # First Name 1
        str += "," + self.last_name             # Last Name 2
        str += "," + self.birthdate.__str__()   # Birthdate 3
        str += "," + self.sex                   # Sex 4
        str += "," + self.email                 # Email 5
        str += "," + self.phone.__str__()       # Phone 6
        str += "," + self.preferred_hospital    # Hospital 7
        str += "\n"
        return str
	
	#Helper functions that add data to the database.
    def user_export_fileName(self):
        return self.username + "-export"

    def user_export(self):
        name = self.user_export_fileName()
        file = open(name, "w")
        str = self.__str__()
        str1 = MedicalHistory.export(username=self.username)
        str += str1
        file = open(name, "w")
        file.write(str)
        file.close()

        return name

    @staticmethod

    def import_user(line):
        line_split = re.split('[,]', line)
        if User.objects.filter(username=line_split[0]) == None:
            raise Exception('There was a user with the same Username!!!')

        user = User.objects.create(username=line_split[0], email=line_split[5],
                                   password='PASSWORD', first_name=line_split[1],
                                   last_name=line_split[2])
        user.save()

        person = Person(person=user, phone=line_split[6],
                        birthdate=line_split[3], first_name=line_split[1],
                        last_name=line_split[2], email=line_split[5],
                        username=line_split[0], usertype='patient', sex=line_split[4],
                        preferred_hospital=line_split[7])
        person.save()

        return person
		
#Small class to facilitate admin workaround.
class PersonAdmin(admin.ModelAdmin):
	list_display = ('username', 'first_name', 'last_name', 'usertype')

	
#Model for appointments
#Appointments are supposed to take doctor, date, description, a user's first and last name and hospital
class Appointment(models.Model):
    doctor = models.CharField(max_length=20)
    date = models.DateTimeField()
    description = models.CharField(max_length=100)
    p_username = models.CharField(max_length=100)
    p_first_name = models.CharField(max_length=100)
    p_last_name = models.CharField(max_length=100)
    hospital = models.CharField(max_length=50)
	
	
	#These functions help assist transfering the data to our database
    def export(self, username):
        return self.export_Appts()

    def export_Appts(self, username):
        appts = Appointment.objects.filter(p_username=username)
        str = ""
        for x in range(0,len(appts)):
            str += appts[x].__str__()
        str = "\n"

    def __str__(self):
        str = "{"
        str += self.doctor
        str += "," + self.p_first_name
        str += "," + self.p_last_name
        str += "," + self.p_username
        str += "," + self.description
        str += "," + self.date
        str += ""
        return str

#Basic model for activites in the activity log.
class Activity(models.Model):
    data = models.CharField(max_length=250)
    date = models.CharField(max_length=50)
