package com.example.spacextestapp

import android.app.Application
import com.example.spacextestapp.di.*

class SpacexApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

//    companion object {
//        val mainComponent: MainComponent by lazy {
//            DaggerMainComponent.create()
//        }
//    }
}