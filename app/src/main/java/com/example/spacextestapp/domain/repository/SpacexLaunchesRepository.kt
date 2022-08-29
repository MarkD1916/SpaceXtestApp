package com.example.spacextestapp.domain.repository

import androidx.paging.PagingData
import com.example.spacextestapp.data.remote.request.LaunchesQuery
import com.example.spacextestapp.domain.model.ListAndDetailData
import kotlinx.coroutines.flow.Flow

interface SpacexLaunchesRepository {

    fun getPagedLaunches(query: LaunchesQuery): Flow<PagingData<ListAndDetailData>>
}