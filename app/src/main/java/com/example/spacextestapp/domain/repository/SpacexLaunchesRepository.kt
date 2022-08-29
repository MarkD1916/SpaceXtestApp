package com.example.spacextestapp.domain.repository

import androidx.paging.PagingData
import com.example.spacextestapp.data.remote.reaponse.entity.LaunchesDto
import com.example.spacextestapp.data.remote.request.LaunchesQuery
import com.example.spacextestapp.domain.model.ListAndDetailData
import kotlinx.coroutines.flow.Flow

interface SpacexLaunchesRepository {
    suspend fun testGetData(query: LaunchesQuery): List<LaunchesDto>

    fun getPagedLauches(query: LaunchesQuery): Flow<PagingData<ListAndDetailData>>
}