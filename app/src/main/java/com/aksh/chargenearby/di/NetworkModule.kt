package com.aksh.chargenearby.di

import com.aksh.chargenearby.data.remote.api.OpenChargeMapApi
import com.aksh.chargenearby.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL =  "https://api.openchargemap.io/v3/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {

            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor { chain ->

                val originalRequest = chain.request()

                val newUrl = originalRequest.url
                    .newBuilder()
                    .addQueryParameter(
                        "key",
                        BuildConfig.OPEN_CHARGE_MAP_API_KEY
                    )
                    .build()

                val newRequest = originalRequest
                    .newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenChargeMapApi(
        retrofit: Retrofit
    ): OpenChargeMapApi {

        return retrofit.create(OpenChargeMapApi::class.java)
    }
}
