from django.conf.urls import url, patterns, include
from . import views

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
               url(r'^userlist/', views.patientlist),
               url(r'^adminpanel/', views.adminpanel),
               url(r'^updateusertype/(\d+)$', views.updateusertype),
               url(r'^updatepatient/(\d+)$', views.updatepatient),
               url(r'^.*/', views.index),

               ]
