# 가상환경
# 생성
python -m venv venv
# 활성화
source venv/Scripts/activate
# 비활성화
deactivate

# 장고 설치
pip install django==3.2.12
# 장고 생성
django-admin startproject Data .
python manage.py runserver
python manage.py startapp music

# library 설치
pip install -r requirements.txt

# settings.py 앱등록

# urls 연결
# app_name=''

##
# mysql연동
pip install mysqlclient
touch my_settings.py
migrate
runserver