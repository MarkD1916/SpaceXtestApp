package com.example.spacextestapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.spacextestapp.data.paging.LaunchesPagingSource
import com.example.spacextestapp.data.remote.LaunchesApi
import com.example.spacextestapp.data.remote.reaponse.LaunchesResponse
import com.example.spacextestapp.data.remote.reaponse.entity.LaunchesDto
import com.example.spacextestapp.data.remote.request.LaunchesQuery
import com.example.spacextestapp.domain.model.ListAndDetailData
import com.example.spacextestapp.domain.repository.SpacexLaunchesRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SpacexLaunchesRepositoryImpl @Inject constructor(
    val launchesApi: LaunchesApi
): SpacexLaunchesRepository {
    override fun getPagedLauches(query: LaunchesQuery): Flow<PagingData<ListAndDetailData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { LaunchesPagingSource(query, launchesApi) }
        ).flow
    }



    override suspend fun testGetData(query: LaunchesQuery): List<LaunchesDto> {
        return launchesApi.getLaunches(query).body()?.launchesData?: throw Exception("Data is NULL")
    }


}