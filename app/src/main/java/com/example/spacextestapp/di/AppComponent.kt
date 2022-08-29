package com.example.spacextestapp.di

import android.app.Application
import android.content.Context
import com.example.spacextestapp.data.remote.LaunchesApi
import com.example.spacextestapp.domain.repository.SpacexLaunchesRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun context(): Context
    fun applicationContext(): Application
    fun getApi(): LaunchesApi
}