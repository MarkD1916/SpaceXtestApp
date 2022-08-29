package com.example.spacextestapp.presentation.list

import android.os.Bundle
import android.os.Parcelable
import com.example.spacextestapp.data.remote.request.LaunchesQuery
import com.example.spacextestapp.domain.model.DetailLaunchData

sealed class LaunchesListEvent {

    data class GetLaunchesPagingData(
        val query: LaunchesQuery
    ): LaunchesListEvent()

    data class OnLaunchesItemClick(
        val action: Int,
        val arguments: Bundle
    ): LaunchesListEvent()

}