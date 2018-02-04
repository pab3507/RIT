from django.contrib import admin

from .models import Person, PersonAdmin

admin.site.register(Person, PersonAdmin)

