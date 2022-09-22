package com.hanyeop.songforyou.di

import com.hanyeop.songforyou.api.KakaoApi
import com.hanyeop.songforyou.utils.KAKAO_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    // Retrofit DI
    @Provides
    @Singleton
    @Named("kakaoRetrofit")
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // KakaoApi DI
    @Provides
    @Singleton
    fun provideKakaoApi(@Named("kakaoRetrofit") retrofit: Retrofit): KakaoApi {
        return retrofit.create(KakaoApi::class.java)
    }
}