package com.example.spacextestapp.di

import androidx.activity.ComponentActivity
import com.example.spacextestapp.presentation.LaunchesViewModel
import com.example.spacextestapp.di.vm.ViewModelModule
import com.example.spacextestapp.presentation.detail.MissionDetailFragment
import com.example.spacextestapp.presentation.list.MissionListFragment
import dagger.BindsInstance
import dagger.Component


@PerActivityFragments
@Component(dependencies = [AppComponent::class],modules = [ViewModelModule::class, RepositoryModule::class])
interface ActivityViewModelComponent {

    fun getLaunchesViewModel(): LaunchesViewModel

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun componentActivity(activity: ComponentActivity): Builder
        fun build(): ActivityViewModelComponent
        fun appComponent(appComponent: AppComponent): Builder
    }

    fun inject(missionListFragment: MissionListFragment)

    fun inject(missionDetailFragment: MissionDetailFragment)
}
