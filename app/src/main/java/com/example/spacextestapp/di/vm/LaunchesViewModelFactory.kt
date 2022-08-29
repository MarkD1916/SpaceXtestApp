package com.example.spacextestapp.di.vm

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.spacextestapp.domain.use_case.LaunchUseCase
import com.example.spacextestapp.presentation.LaunchesViewModel

//ToDo если bundle будет не нужег - нужно его занулить
class LaunchesViewModelFactory(
    val launchUseCase: LaunchUseCase,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return LaunchesViewModel(launchUseCase) as T
    }
}