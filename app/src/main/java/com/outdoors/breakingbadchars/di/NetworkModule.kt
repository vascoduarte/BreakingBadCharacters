package com.outdoors.breakingbadchars.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.outdoors.breakingbadchars.network.BreakingBadService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOKHttpClientInterceptor():HttpLoggingInterceptor{
        return  HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOKHttpClient(interceptor: HttpLoggingInterceptor):OkHttpClient{
        return  OkHttpClient.Builder().addInterceptor(interceptor).build()

    }
    @Singleton
    @Provides
    fun provideJsonConfig():Json{
        val jConf = JsonConfiguration.Stable.copy(
            ignoreUnknownKeys = true)
        jConf.copy(isLenient = true)

        return Json(jConf)
    }
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, json:Json) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.breakingbadapi.com/")
            .client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): BreakingBadService {
        return retrofit.create(BreakingBadService::class.java)
    }
}