package com.example.spacextestapp.di

import com.example.spacextestapp.data.remote.LaunchesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLaunchesApi(retrofit: Retrofit): LaunchesApi {
        return retrofit.create(LaunchesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private companion object {
        const val BASE_URL = "https://api.spacexdata.com/v4/"
    }



}