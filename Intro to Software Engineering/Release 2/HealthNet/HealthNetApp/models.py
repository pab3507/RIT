from django.db import models
from django.contrib.auth.models import User
import re
'''
Author: TeamD-MoisesIsLateAgain
Date: 2/20/2017
Models.py
File containing all the models and related functions for the models.
Person is the base model for all users, to determine the actual usertype look at the usertype field
Person is attached to auth.users to validate passwords and other fields.
'''


class Person(models.Model):
    person = models.OneToOneField(User, related_name='person')
    first_name = models.CharField(max_length=25)
    last_name = models.CharField(max_length=25)
    username = models.CharField(max_length=25)
    password = models.CharField(max_length=32)
    birthdate = models.DateField()
    email = models.EmailField(max_length=48)
    phone = models.CharField(max_length=15, default=0)
    sex = models.CharField(max_length=15)
    preferred_hospital = models.CharField(max_length=50)
    usertype = models.CharField(max_length=8)
    primary_physician = models.CharField(max_length=50)
    last_physical = models.CharField(max_length=50)
    health_history = models.CharField(max_length=600)
    status = models.CharField(max_length=10)

    def __str__(self):
        str = self.username  # Username 0
        str += "," + self.first_name  # First Name 1
        str += "," + self.last_name  # Last Name 2
        str += "," + self.birthdate.__str__()  # Birthdate 3
        str += "," + self.sex  # Sex 4
        str += "," + self.email  # Email 5
        str += "," + self.phone.__str__()  # Phone 6
        str += "," + self.preferred_hospital  # Hospital 7
        str += ',' + self.health_history
        str += "\n"
        return str

    def user_export(self):
        file_text = ''
        string = self.__str__()
        newstring = string.split(',')
        username = newstring[0].split(' ')
        file_text += 'Username: ' + username[1] + '\n'
        file_text += 'Full Name: ' + newstring[1] + ' ' + newstring[2] + '\n'
        file_text += 'DOB: ' + newstring[3] + '\n'
        file_text += 'Sex: ' + newstring[4] + '\n'
        file_text += 'Email: ' + newstring[5] + '\n'
        file_text += 'Phone Number: ' + newstring[6] + '\n'
        file_text += 'Preferred Hospital: ' + newstring[7] + '\n\n'
        endofstring = newstring[8].split('>')
        file_text += 'Health History: \n------------\n' + endofstring[0] + '\n\n'
        return file_text

    def get_username(self):
        string = self.__str__()
        string = string.split(',')
        username = string[0].split(' ')
        return username[1]

    @staticmethod
    def import_user(line):  # Import will only import patients.
        line_split = re.split('[,]', line)
        if User.objects.filter(username=line_split[0]) is None:  # Cant have two users with the same username
            raise Exception('Error, Username already exists in the System')

        user = User.objects.create(username=line_split[0], email=line_split[5],
                                   password='password', first_name=line_split[1],
                                   last_name=line_split[2])
        user.save()

        person = Person(person=user, phone=line_split[6],
                        birthdate=line_split[3], first_name=line_split[1],
                        last_name=line_split[2], email=line_split[5],
                        username=line_split[0], usertype='patient', sex=line_split[4],
                        preferred_hospital=line_split[7])
        person.save()

        return person


class Appointment(models.Model):
    doctor = models.CharField(max_length=20)
    date = models.DateTimeField()
    description = models.CharField(max_length=100)
    p_username = models.CharField(max_length=100)
    p_first_name = models.CharField(max_length=100)
    p_last_name = models.CharField(max_length=100)
    hospital = models.CharField(max_length=50)

    def export(self, username):
        return self.export_Appts()

    def export_Appts(self, username):
        appts = Appointment.objects.filter(p_username=username)
        str = ""
        x = 0
        while x < len(appts):
            str += appts[x].__str__()
            x += 1
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


class MedicalHistory(models.Model):
    p_username = models.CharField(max_length=50)
    textHistory = models.CharField(max_length=500)

    def export(username):
        medical_history = MedicalHistory.objects.filter(p_username=username)
        string = medical_history[0].__str__()
        return string

    def __str__(self):
        str = self.patient              # 0
        str += "," + self.textHistory   # 1
        str += "\n"
        return str

    def import_History(line):
        text = re.split('[,]', line)
        med = MedicalHistory(p_username=text[0], textHistory=text[1])
        med.save()
        return med


class Prescription(models.Model):
    doctor = models.CharField(max_length=50)
    patient = models.CharField(max_length=50)
    drugname = models.CharField(max_length=100)
    description = models.CharField(max_length=500)
    dosage = models.CharField(max_length=100)
    quantity = models.CharField(max_length=50)
    instructions = models.CharField(max_length=500)

    def export(username):
        curr_prescriptions = Prescription.objects.filter(patient=username)   # Get the prescriptions for a user
        str = ""
        x = 0
        while x < len(curr_prescriptions):
            str += curr_prescriptions[x].__str__()
            x += 1
        str += "\n"
        return str

    def __str__(self):
        str = "{"
        str += self.patient             # 0 patient name
        str += "," + self.doctor        # 1 doctor name
        str += "," + self.drugname      # 2 drug name
        str += "," + self.dosage        # 3 dosage
        str += "," + self.quantity      # 4 quantity
        str += "," + self.description   # 5 description
        str += "," + self.instructions  # 6 instructions
        str += ""
        return str

    def import_Prescription(line):
        string = ""
        vars = re.split("[{]", line)
        for x in range(1, len(vars)):
            vars[x] = re.split(",", vars[x])
            new_prescription = Prescription(patient=vars[x][0], doctor=vars[x][1], drugname=vars[x][2],
                               dosage=vars[x][3], quantity=vars[x][4], description=vars[x][5],
                               instructions=vars[x][6])
            new_prescription.save()
            string += new_prescription.__str__()
        return string


class Notification(models.Model):
    type = models.CharField(max_length=50)
    date = models.CharField(max_length=100)
    user = models.CharField(max_length=50)
    doctor = models.CharField(max_length=50)
    data = models.CharField(max_length=200)
    data2 = models.CharField(max_length=200)


def handle_uploaded_file(filename):  # Function that will call the correct import functions when reading a file.
    file = open(filename, 'r')  # Open the file to read from
    lines = file.readlines()    # Read the lines
    file.close()                # Close the file
    string = ""
    x = 0
    while x < len(lines):
        line = lines[x]
        if x == 0:
            string += "y"
            Person.import_user(line)
            x += 1
        elif x == 1:
            string += "y"
            MedicalHistory.import_History(line)
            x += 1
        else:
            string += "y"
            Prescription.import_Prescription(line)
            x += 1
    return string


class Message(models.Model):
    sender = models.CharField(max_length=50)
    receiver = models.CharField(max_length=50)
    message = models.CharField(max_length=500)
    date = models.CharField(max_length=50)


class MedicalTest(models.Model):
    patient = models.CharField(max_length=50)
    testname = models.CharField(max_length=50)
    results = models.CharField(max_length=500)
    date = models.CharField(max_length=50)
    pending = models.CharField(max_length=10)


class Activity(models.Model):
    data = models.CharField(max_length=250)
    date = models.CharField(max_length=50)


class SystemStats(models.Model):
    totalAdmins = models.IntegerField()
    totalDoctors = models.IntegerField()
    totalNurses = models.IntegerField()
    totalPatients = models.IntegerField()
    successfulLogins = models.IntegerField()
    failedLogins = models.IntegerField()
    appointmentsCreated = models.IntegerField()
    medicationsPrescribed = models.IntegerField()
    testsConducted = models.IntegerField()
    messagesSent = models.IntegerField()
    notificationsReceived = models.IntegerField()
    patientsAdmitted = models.IntegerField()
    oldestUser = models.CharField(max_length=50)
