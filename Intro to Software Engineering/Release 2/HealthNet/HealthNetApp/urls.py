from django.conf.urls import url, patterns, include
from . import views

'''
Author: TeamD MoisesIsLateAgain
Date: 3/10/2017
urls.py
This file specifies the urls of the project and where to direct them in views.py
'''

urlpatterns = [url(r'^$', views.index),
               url(r'^dashboard/', views.dashboard),
               url(r'^login/', views.userLogin),
               url(r'^register/', views.userRegister),
               url(r'^logout/', views.userLogout),
               url(r'^editprofile/', views.profileEdit),
               url(r'^profile/', views.profileView),
               url(r'^createAppointment/', views.createAppointment),
               url(r'^editAppointment/(\d+)$', views.editAppointment),
               url(r'^deleteAppointment/(\d+)$', views.deleteAppointment),
               url(r'^appointments/', views.appointments),
               url(r'^exportUser/(\d+)$', views.exportUser),
               url(r'^prescriptions/', views.prescriptions),
               url(r'^testresults/', views.testresults),
               url(r'^medicalhistory/', views.medicalhistory),
               url(r'^userlist/', views.patientlist),
               url(r'^prescribe/', views.prescribe),
               url(r'^notifications/', views.notifications),
               url(r'^uploadpatient/', views.uploadpatient),
               url(r'^messages/', views.messages),
               url(r'^sendmessage/', views.sendmessage),
               url(r'^pendingtests/', views.pendingtests),
               url(r'^requesttest/', views.requesttest),
               url(r'^returnresults/(\d+)$', views.returnresults),
               url(r'^admitpatient/(\d+)$', views.admitpatient),
               url(r'^dischargepatient/(\d+)$', views.dischargepatient),
               url(r'^adminpanel/', views.adminpanel),
               url(r'^permissions/', views.permissions),
               url(r'^updateusertype/(\d+)$', views.updateusertype),
               url(r'^systemstats/', views.systemstats),
               url(r'^removeprescription/', views.removeprescription),
               url(r'^removeprescript/(\d+)$', views.removeprescript),
               url(r'^updatepatient/(\d+)$', views.updatepatient),
               url(r'^.*/', views.index),

               ]