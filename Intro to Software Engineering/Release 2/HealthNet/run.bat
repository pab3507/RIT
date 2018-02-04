
echo OFF
TITLE HealthNet Server
echo Would you like to launch the HealthNet Server?
pause
python manage.py makemigrations
python manage.py migrate
python manage.py runserver