from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializers import SoundSerializer
from .models import SoundFeature
from rest_framework import status

import librosa
from sklearn.metrics.pairwise import cosine_similarity
import pandas as pd
import sklearn
import os

# from urllib.request import urlopen
import urllib.request

# Create your views here.
@ api_view(['POST','PUT'])
# @ api_view(['GET'])
def record(request,userSeq):
    # 나의 노래 분석
    # 오디오 파일 가져와서 분석
    # -> 성공하면 분석결과 테이블에 저장하고 true 반환
    # -> 실패하면 false 반환
    # 오디오 파일 삭제

    # 오디오 s3 주소
    # 오디오 저장
    song_url = request.data.get('url')
    print(request.data.get('url'))
    # https://songforyou.s3.ap-northeast-2.amazonaws.com/songRecord/626296fe-70f3-4e99-bbc8-a4a8057229f1.mp3
    # 오디오 링크를 주지 않으면 false
    if not (song_url):
        print(song_url)
        result = {
            'message': "녹음파일 분석",
            'status': "false",
	        "data": {
            }
        }
        return Response(result)
    # 녹음 파일 다운
    save_name = 'music\music\my.mp3'
    urllib.request.urlretrieve(song_url,save_name)

    # 노래 음원 분석
    data = {}
    data['user_pk'] = userSeq
    # 노래 음원
    y,sr = librosa.load(save_name)
    print('good')
    
    # 템포
    tempo, _ = librosa.beat.beat_track(y, sr=sr)
    data['tempo'] = tempo
    # 양음,음양 바뀌는 구간
    zero_crossings = librosa.zero_crossings(y, pad=False)
    data['zero_crossing_rate_mean'],data['zero_crossing_rate_var'] = zero_crossings.mean(), zero_crossings.var()
    # 오디오 시계열을 분해
    y_harm, y_perc = librosa.effects.hpss(y) 
    data['harmony_mean'], data['harmony_var'] = y_harm.mean(),y_harm.var() # 사람의 귀로 구분할 수 없는 특징들(음악의 색깔)
    data['perceptr_mean'], data['perceptr_var'] = y_perc.mean(), y_perc.var() # 리듬과 감정을 나타내는 충격파
    # 소리의 "무게 중심"이 어딘지를 알려주는 지표
    spectral_centroids = librosa.feature.spectral_centroid(y, sr=sr)[0]
    data['spectral_centroid_mean'], data['spectral_centroid_var'] = spectral_centroids.mean(), spectral_centroids.var()
    # 신호 모양을 측정
    spectral_rolloff = librosa.feature.spectral_rolloff(y, sr=sr)[0]
    data['rolloff_mean'], data['rolloff_var'] = spectral_rolloff.mean(),spectral_rolloff.var()
    # 크로마 특징은 음악의 흥미롭고 강렬한 표현
    chromagram = librosa.feature.chroma_stft(y, sr=sr, hop_length=512)
    data['chroma_stft_mean'], data['chroma_stft_var'] = chromagram.mean(), chromagram.var()
    # p'차 스펙트럼 대역폭을 계산
    spectral_bandwidth = librosa.feature.spectral_bandwidth(y=y,sr=sr)
    data['spectral_bandwidth_mean'], data['spectral_bandwidth_var'] = spectral_bandwidth.mean(),spectral_bandwidth.var()
    # 사람의 청각 구조를 반영하여 음성 정보 추출
    mfccs = librosa.feature.mfcc(y, sr=sr)
    for i in range(len(mfccs)):
        data['mfcc'+ str(i) + '_mean'], data['mfcc' + str(i) + '_var'] = mfccs[i].mean(),mfccs[i].var()

    # 녹음 처음 -> 등록 / 아니라면 -> 수정
    if SoundFeature.objects.filter(user_pk = userSeq).exists():
        sound = SoundFeature.objects.get(user_pk=userSeq)
        # print(sound)
        serializer = SoundSerializer(sound,data=data)
    else:
        serializer = SoundSerializer(data=data)

    if serializer.is_valid(raise_exception=True):
        serializer.save()
        result = {
            'message': "녹음파일 분석",
            'status': "true",
            "data": {
            }
        }
        # 오디오 파일 삭제
        os.remove(save_name)
        return Response(result)
        

@ api_view(['GET'])
def recommend(request,userSeq,cnt):

    # userSeq와 비슷한 음색 가진 유저 찾아서 상위 cnt명 돌려주기
    sound = SoundFeature.objects.all()
    sound_df = pd.DataFrame(list(sound.values()))
    # 값 표준화 (id, user_pk 빼고)
    labels = sound_df[['user_pk']]
    print(labels['user_pk'])
    sound_df = sound_df.drop(columns=['id','user_pk'])
    sound_df_scaled = sklearn.preprocessing.scale(sound_df)
    sound_df = pd.DataFrame(sound_df_scaled,columns=sound_df.columns)
    # 유사도 체크
    # me = pd.Series(sound_df.loc[userSeq])
    similar = cosine_similarity(sound_df)
    # sim_df = pd.DataFrame(similar,index=labels.index,columns=labels.index).loc[userSeq]
    sim_df = pd.DataFrame(similar,index=labels['user_pk'],columns=labels['user_pk'])
    me = sim_df.loc[userSeq].sort_values(ascending=False)[1:cnt]
    # 유사도가 0.5보다는 커야하지 않을까?
    # return Response(me[me>0.5].index)
    result = {
        'message': "비슷한 목소리 유저 리스트",
        'status': "true",
        "data": me.index
    }
    return Response(result)

