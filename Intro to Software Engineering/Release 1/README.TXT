TeamD 

_________________________________________________________________________________

1.To access the website first open command prompt.

2.Change directory where you stored the files (wherever manage.py is stored)

3.Run the command $python manage.py runserver

4.This will launch the server on a localhost with port 8000 as default

5.Open preffered browser and go to local host.
_________________________________________________________________________________

To view the activity log go to /login/ and use the admin account
Or log into the admin page and click viewpage in the top right.


Doctor account: 
	User				Password
-----------------------------------------------------------
	doctor1				password

 
Nurse account:
	User				Password
-----------------------------------------------------------
	nurse1				password


Admin account:
	User				Password
-----------------------------------------------------------
	admin1				admin


Patient account:
	User				Password
-----------------------------------------------------------
	User				user


Our Calander uses Jquery's fullcalander
and other Jquery documents.

KnownBugs:
Can't make appointment without description. Appointments are not registering doctors at the moment therefore cannot make any appointments.
Doctors cannot see their patients so they cannot make their appointments either.
There are some dead url links
Creating admins via superuser does not link properly to the model. We added a work around in the admin page.
Also creating doctors and nurses use this workaround. Need to attach person to user in the admin person page.
