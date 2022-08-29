package com.example.spacextestapp.di

import com.example.spacextestapp.data.repository.SpacexLaunchesRepositoryImpl
import com.example.spacextestapp.domain.repository.SpacexLaunchesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideLaunchesRepo(repo: SpacexLaunchesRepositoryImpl): SpacexLaunchesRepository

}