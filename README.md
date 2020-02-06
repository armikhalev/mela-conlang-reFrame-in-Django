To activate venv in fish:
- . venv/bin/activate.fish

To generate static files run:
- python manage.py collectstatic

if starting from scratch need to add all the packages, use pip:
`pip install django-cors-headers`
`pip install djangorestframework`
`pip install djangorestframework-jsonapi`
`pip install djangorestframework-jwt`
`pip install psycopg2`

## DEV
- don't forget to set `DEBUG=False`, otherwise static files won't be available
- then try `python manage.py runserver`

NOTE: `settings.py` is not in git repo, it was easier to keep it private than deal with three files system for prod, dev and common. So, it should be privately shared.