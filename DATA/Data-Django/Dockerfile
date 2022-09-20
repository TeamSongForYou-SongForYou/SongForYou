FROM python:3.9.12
WORKDIR /var/lib/jenkins/workspace/songforyou_django/DATA/Data-Django/
COPY requirements.txt ./

RUN pip install --upgrade pip
RUN pip install -r requirements.txt
RUN apt-get install gstreamer1.0-plugins-base gstreamer1.0-plugins-ugly
RUN apt-get update
RUN apt-get install ffmpeg
RUN apt-get update
RUN apt-get install -y libsndfile1-dev
COPY . .
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]
