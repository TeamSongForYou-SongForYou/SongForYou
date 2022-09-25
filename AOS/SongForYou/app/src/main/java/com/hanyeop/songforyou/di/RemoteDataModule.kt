package com.hanyeop.songforyou.di

import com.hanyeop.songforyou.api.IbRecommendApi
import com.hanyeop.songforyou.api.KakaoApi
import com.hanyeop.songforyou.api.Oauth2Api
import com.hanyeop.songforyou.utils.BASE_URL
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

    // KakaoRetrofit DI
    @Provides
    @Singleton
    @Named("kakaoRetrofit")
    fun provideKakaoRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Retrofit DI
    @Provides
    @Singleton
    @Named("retrofit")
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // KakaoApi DI
    @Provides
    @Singleton
    fun provideKakaoApi(@Named("kakaoRetrofit") retrofit: Retrofit): KakaoApi {
        return retrofit.create(KakaoApi::class.java)
    }

    // Oauth2Api DI
    @Provides
    @Singleton
    fun provideOauth2Api(@Named("retrofit") retrofit: Retrofit): Oauth2Api {
        return retrofit.create(Oauth2Api::class.java)
    }

    // IbRecommendApi DI
    @Provides
    @Singleton
    fun provideIbRecommendApi(@Named("retrofit") retrofit: Retrofit): IbRecommendApi {
        return retrofit.create(IbRecommendApi::class.java)
    }
}