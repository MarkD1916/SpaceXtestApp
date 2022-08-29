package com.example.spacextestapp.presentation.list

import androidx.paging.PagingData
import com.example.spacextestapp.domain.model.ListAndDetailData
import kotlinx.coroutines.flow.Flow

data class LaunchesListState(
    var items: Flow<PagingData<ListAndDetailData>>? = null
)
