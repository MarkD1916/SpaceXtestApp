package com.example.spacextestapp.di.vm

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.spacextestapp.di.PerActivityFragments
import com.example.spacextestapp.domain.repository.SpacexLaunchesRepository
import com.example.spacextestapp.domain.use_case.LaunchUseCase
import com.example.spacextestapp.domain.use_case.GetLaunchesPagingResult
import com.example.spacextestapp.presentation.LaunchesViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    @PerActivityFragments
    fun provideUseCase(repository:SpacexLaunchesRepository): LaunchUseCase{
        return LaunchUseCase(GetLaunchesPagingResult(repository))
    }

    @Provides
    @PerActivityFragments
    fun provideMyViewModel(
        activity:ComponentActivity,
        dependencies: LaunchUseCase
    ): LaunchesViewModel {
        return ViewModelProvider(
            activity.viewModelStore,
            LaunchesViewModelFactory(
                dependencies,
                activity
            )
        )[LaunchesViewModel::class.java]
    }
}