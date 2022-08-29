package com.example.spacextestapp.domain.use_case

import androidx.paging.PagingData
import com.example.spacextestapp.data.remote.reaponse.entity.LaunchesDto
import com.example.spacextestapp.data.remote.request.LaunchesQuery
import com.example.spacextestapp.domain.model.ListAndDetailData
import com.example.spacextestapp.domain.repository.SpacexLaunchesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetLaunchesPagingResult @Inject constructor(val repository: SpacexLaunchesRepository) {
    fun execute(query: LaunchesQuery): Flow<PagingData<ListAndDetailData>> {
        return repository.getPagedLauches(query)
    }
}