# Generated by Django 3.0.2 on 2020-01-25 03:22

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('koyla', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='card',
            name='grammarCard',
            field=models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='koyla.GrammarCard'),
        ),
        migrations.AlterField(
            model_name='grammarcard',
            name='category',
            field=models.CharField(max_length=64),
        ),
        migrations.AlterField(
            model_name='koyla',
            name='comment',
            field=models.CharField(max_length=1000),
        ),
        migrations.AlterField(
            model_name='koyla',
            name='grammarCard',
            field=models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, to='koyla.GrammarCard'),
        ),
    ]