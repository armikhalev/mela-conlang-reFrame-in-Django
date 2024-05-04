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
- in the second terminal go to `re-frame` directory and run `lein dev` to run re-frame app's figwheel server (otherwise it will not be recompiling your changes and will complain about not reaching the figwheel)
- go to http://127.0.0.1:8000/koyla it should work!
- comment out (g/check) if you want to see Ghostwheel spec-checking!
Read more about frontEnd work in `re-frame/README.md`.

Run `lein build` for Prod build.

NOTE: `settings.py` is not in git repo, it was easier to keep it private than deal with three files system for prod, dev and common. So, it should be privately shared.

## DB
If you want to dump your DB from Pythonanywhere to a file, go to bash terminal in Pythonanywhere, then: 
`pg_dump --host=Melasi-1470.postgres.pythonanywhere-services.com --port=11470 --username=super --file=pgbackup-2024-5-4.sql mela_backend_2`
then in your local terminal run: `psql -f ./pgbackup-2024-5-4.sql` 
Read more: https://www.dbvis.com/thetable/a-complete-guide-to-pg_dump-with-examples-tips-and-tricks/