import datetime
from django import forms
from django.contrib.auth import authenticate
from django.forms import ModelForm
from django.forms import widgets
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User
from .models import Appointment, Person
import re

'''
### Authors: TeamD MoisesIsLateAgain
### Members: Bryan Camp, Yancarlos Diaz, Tyler Collins, Michael Hopkins, and Moisés Lora Pérez
### File Description: 
### These pages are used by the django.contrib.auth import to ensure forms are filled out correctly.
### Each form is utilized in cleanData functions which are in turn called in views.py
'''

class PersonLoginForm(ModelForm):

    def __init__(self, *args, **kwargs):
        super(PersonLoginForm, self).__init__(*args, **kwargs)
        self.fields['username'].widget.attrs['class'] = 'form-control'
        self.fields['username'].widget.attrs['placeholder'] = 'Enter your username'
        self.fields['password'].widget.attrs['placeholder'] = 'Enter your password'
    class Meta:
        model = Person
        widgets = {
            'password': forms.PasswordInput(attrs={'class': 'form-control', 'placeholder': 'Enter password'}),
        }

        fields = ['username', 'password']
    def clean_username(self):
        uname = self.cleaned_data['username']
        if not User.objects.filter(username=uname).exists():
            raise forms.ValidationError('User does not exist')
        return uname

    def clean_password(self):
        user = authenticate(username='username', password='password')
        if user is None:
            raise forms.ValidationError('Username and password do not match')
        return user


class AppointmentForm(forms.Form):
    doctor = forms.CharField(widget=forms.widgets.Select())
    p_first_name = forms.CharField(widget=forms.widgets.Select())
    description = forms.CharField(widget=forms.Textarea(attrs={'rows': 5}))
    date = forms.CharField(widget=forms.widgets.DateInput(attrs={'type': 'date'}))
    time = forms.CharField(widget=forms.widgets.TimeInput(attrs={'type': 'time'}))

    class Meta:
        model = Appointment
        fields = ['doctor', 'date', 'time', 'description', 'p_username', 'p_first_name', 'p_last_name']

    def validate(self):
        return true

    def __init__(self, *args, **kwargs):
        super(AppointmentForm, self).__init__(*args, **kwargs)
        self.fields['doctor'].widget.attrs['class'] = 'form-control'
        self.fields['p_first_name'].widget.attrs['class'] = 'form-control'
        self.fields['date'].widget.attrs['class'] = 'form-control'
        self.fields['date'].widget.attrs['value'] = '2017-03-02'
        self.fields['time'].widget.attrs['class'] = 'form-control'
        self.fields['time'].widget.attrs['value'] = '14:30'
        self.fields['time'].widget.attrs['step'] = '1800'
        self.fields['description'].widget.attrs['class'] = 'form-control'
        self.fields['description'].widget.attrs['autofocus'] = ''
        self.fields['description'].widget.attrs \
            .update({'placeholder': 'Reason for appointment (i.e.symptoms)', 'style': 'resize:none;'})


class EditProfileForm(ModelForm):
    birthdate = forms.CharField(widget=forms.widgets.DateInput(attrs={'type': 'date'}))
    last_physical = forms.CharField(widget=forms.widgets.DateInput(attrs={'type': 'date'}))
    SEX_CHOICES = (('Female', 'Female'), ('Male', 'Male'))
    sex = forms.CharField(widget=forms.widgets.Select(choices=SEX_CHOICES))
    #health_history = forms.CharField(widget=forms.Textarea(attrs={'rows': 3}))

    def __init__(self, *args, **kwargs):
        super(EditProfileForm, self).__init__(*args, **kwargs)
        self.fields['first_name'].widget.attrs['class'] = 'form-control'
        self.fields['last_name'].widget.attrs['class'] = 'form-control'
        self.fields['birthdate'].widget.attrs['class'] = 'form-control'
        self.fields['birthdate'].label = 'Birthdate'
        self.fields['email'].widget.attrs['class'] = 'form-control'
        self.fields['phone'].widget.attrs['class'] = 'form-control'
        self.fields['sex'].widget.attrs['class'] = 'form-control'
        self.fields['last_physical'].widget.attrs['class'] = 'form-control'
       # self.fields['health_history'].widget.attrs['class'] = 'form-control'
       # self.fields['health_history'].widget.attrs['style'] = 'resize:none;'

    class Meta:
        model = Person
        fields = ['first_name', 'last_name', 'birthdate', 'email', 'sex', 'phone',
                  'last_physical']# 'health_history']


class ViewProfileForm(ModelForm):
    birthdate = forms.CharField(widget=forms.widgets.DateInput(attrs={'type': 'date'}))

    class Meta:
        model = Person
        widgets = {
            'password': forms.PasswordInput(),
        }
        fields = ['first_name', 'last_name', 'birthdate', 'email', 'phone']


class RegisterForm(forms.Form):
    username = forms.CharField(max_length=200)
    email = forms.EmailField(max_length=200)
    password = forms.CharField(widget=forms.PasswordInput(), max_length=100)
    first_name = forms.CharField(max_length=100)
    last_name = forms.CharField(max_length=100)
    birthdate = forms.DateField(widget=forms.widgets.DateInput(attrs={'type': 'date'}))
    last_physical = forms.DateField(widget=forms.widgets.DateInput(attrs={'type': 'date'}))
    phone = forms.CharField(max_length=100)
    SEX_CHOICES = (('Female', 'Female'), ('Male', 'Male'))
    sex = forms.CharField(widget=forms.widgets.Select(choices=SEX_CHOICES))
    HOSPITAL_CHOICES = (('Rochester Hospital', 'Rochester Hospital'),
                        ('Scared Heart', 'Scared Heart'))
    preferred_hospital = forms.CharField(widget=forms.widgets.Select(choices=HOSPITAL_CHOICES))


    def __init__(self, *args, **kwargs):
        super(RegisterForm, self).__init__(*args, **kwargs)
        self.fields['username'].widget.attrs['class'] = 'form-control'
        self.fields['username'].widget.attrs['autofocus'] = ''
        self.fields['username'].widget.attrs['placeholder'] = 'johndoe96'
        self.fields['email'].widget.attrs['class'] = 'form-control'
        self.fields['email'].widget.attrs['placeholder'] = 'something@example.com'
        self.fields['password'].widget.attrs['class'] = 'form-control'
        self.fields['password'].widget.attrs['placeholder'] = 'Enter a password'
        self.fields['first_name'].widget.attrs['class'] = 'form-control'
        self.fields['first_name'].widget.attrs['placeholder'] = 'John'
        self.fields['last_name'].widget.attrs['class'] = 'form-control'
        self.fields['last_name'].widget.attrs['placeholder'] = 'Doe'
        self.fields['birthdate'].widget.attrs['class'] = 'form-control'
        self.fields['birthdate'].widget.attrs['value'] = '1990-01-01'
        self.fields['last_physical'].widget.attrs['class'] = 'form-control'
        self.fields['last_physical'].widget.attrs['value'] = '1990-01-01'
        self.fields['phone'].widget.attrs['class'] = 'form-control'
        self.fields['sex'].widget.attrs['class'] = 'form-control'
        self.fields['preferred_hospital'].widget.attrs['class'] = 'form-control'

    # Ensures the username is not already in use
    def clean_username(self):
        uname = self.cleaned_data['username']
        if User.objects.filter(username=uname).exists():
            raise forms.ValidationError('Username has been taken!')
        return uname

    # Ensures that the email is not already in use and that an email is provided
    def clean_email(self):
        e_mail = self.cleaned_data['email']
        if User.objects.filter(email=e_mail).exists():
            raise forms.ValidationError("email already in use")
        if e_mail == '':
            raise forms.ValidationError('This field is required. Please enter an email.')
        return e_mail

    # Makes sure there is something in the password field
    def clean_password(self):
        password = self.cleaned_data['password']
        if password == '':
            raise forms.ValidationError('This field is required!')
        return password

    def clean_first_name(self):
        first_name = self.cleaned_data['first_name']
        if first_name == '':
            raise forms.ValidationError('This field is required!')
        return first_name

    def clean_last_name(self):
        last_name = self.cleaned_data['last_name']
        if last_name == '':
            raise forms.ValidationError('This field is required!')
        return last_name

    def clean_birthdate(self):
        birthdate = self.cleaned_data['birthdate']
        if birthdate > datetime.date.today():
            raise forms.ValidationError("Cannot be born in the future")
        return birthdate

    def clean_last_physical(self):
        last_physical = self.cleaned_data['last_physical']
        return last_physical


class UploadPatientForm(forms.Form):
    file = forms.FileField()
    def __init__(self, *args, **kwargs):
        super(UploadPatientForm, self).__init__(*args, **kwargs)
        self.fields['file'].widget.attrs['class'] = 'form-control btn-file btn btn-default'

class UpdateUsertypeForm(forms.Form):
    usertype = forms.CharField(widget=forms.widgets.Select())

    class Meta:
        fields = ['usertype']

    def __init__(self, *args, **kwargs):
        super(UpdateUsertypeForm, self).__init__(*args, **kwargs)
        self.fields['usertype'].widget.attrs \
            .update({'class': 'form-control', 'style': 'resize:none;'})


class UpdatePatientForm(forms.Form):
    u_last_physical = forms.CharField(widget=forms.widgets.DateInput(attrs={'type': 'date'}))
    u_primary_physician = forms.CharField(widget=forms.widgets.Select())
    u_health_history = forms.CharField(widget=forms.Textarea(attrs={'rows': 3}))
    u_preferred_hospital = forms.CharField(widget=forms.widgets.Select())

    class Meta:
        fields = ['usertype']

    def __init__(self, *args, **kwargs):
        super(UpdatePatientForm, self).__init__(*args, **kwargs)
        self.fields['u_health_history'].widget.attrs \
            .update({'class': 'form-control', 'style': 'resize:none;'})
        self.fields['u_primary_physician'].widget.attrs['class'] = 'form-control'
        self.fields['u_last_physical'].widget.attrs['class'] = 'form-control'
        self.fields['u_preferred_hospital'].widget.attrs['class'] = 'form-control'
