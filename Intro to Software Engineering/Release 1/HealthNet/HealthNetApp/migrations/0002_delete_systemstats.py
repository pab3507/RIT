# -*- coding: utf-8 -*-
# Generated by Django 1.9.1 on 2017-03-06 16:14
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('HealthNetApp', '0001_initial'),
    ]

    operations = [
        migrations.DeleteModel(
            name='SystemStats',
        ),
    ]