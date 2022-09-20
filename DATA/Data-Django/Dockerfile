FROM python:3.9.5
WORKDIR /var/lib/jenkins/workspace/songforyou_django/DATA/Data-Django/
COPY requirements.txt ./

RUN pip install --upgrade pip
RUN pip install -r requirements.txt
RUN pip install gunicorn
COPY . .
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]
