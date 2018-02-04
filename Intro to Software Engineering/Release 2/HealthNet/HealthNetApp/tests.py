from django.test import TestCase
from .models import Person
from django.contrib.auth.models import User
from .models import handle_uploaded_file

'''
Author: TeamD-MoisesIsLateAgain
Date: 2/20/2017
tests.py
File containing all the test related functions for the tests.
Tests uplading a person and file aswell as uploading.
'''
class UploadTestUserRegex(TestCase):

    def SetupTest(self):
        line1 = self.TestPerson()

    def TestPerson(self):
        user = User.objects.create_user(username='test1', email='mbh4480@gmail.com',
                                        password='pass', first_name='Mike',
                                        last_name='Hopkins')
        user.save()
        person = Person(person=user, phone=5555555555,
                         birthdate='1997-30-01', first_name='Mike',
                         last_name='Hopkins', email='mbh4480@gmail.com',
                         username='test1', usertype='patient', sex='Male',
                         preferred_hospital='H1')
        person.save()
        str = person.__str__()

        user.delete()
        person.delete()

        return str

    # line = "[Username,User,Name,11/11/2011,email,6149479011]"

    def CreateFileForTest(self):
        user = User.objects.create_user(username='test2', email='mbh4480@gmail.com',
                                        password='pass', first_name='Mike',
                                        last_name='Hopkins')
        user.save()
        person = Person(person=user, phone=5555555555,
                         birthdate='1997-30-01', first_name='Mike',
                         last_name='Hopkins', email='mbh4480@gmail.com',
                         username='test2', usertype='patient', sex='Male',
                         preferred_hospital='H1')
        person.save()
        filename = person.user_export()
        user.delete()
        person.delete()
        return filename

    def ImportUserTest(self):
        line1 = self.Create_Test_PersonUser()
        self.assertEqual(Person.import_user(line=line1).__str__(), line1 + "\n")

    def ImportFileTest(self):
        conferm = handle_uploaded_file(filename=self.Create_file_for_testing())
        self.assertEqual(conferm, "yyy")
