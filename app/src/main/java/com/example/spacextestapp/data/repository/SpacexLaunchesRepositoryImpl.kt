package com.example.spacextestapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.spacextestapp.data.paging.LaunchesPagingSource
import com.example.spacextestapp.data.remote.LaunchesApi
import com.example.spacextestapp.data.remote.request.LaunchesQuery
import com.example.spacextestapp.domain.model.ListAndDetailData
import com.example.spacextestapp.domain.repository.SpacexLaunchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SpacexLaunchesRepositoryImpl @Inject constructor(
    val launchesApi: LaunchesApi
): SpacexLaunchesRepository {
    override fun getPagedLaunches(query: LaunchesQuery): Flow<PagingData<ListAndDetailData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { LaunchesPagingSource(query, launchesApi) }
        ).flow
    }

}