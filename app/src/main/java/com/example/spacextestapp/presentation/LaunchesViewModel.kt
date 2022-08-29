package com.example.spacextestapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.spacextestapp.data.remote.request.*
import com.example.spacextestapp.domain.use_case.LaunchUseCase
import com.example.spacextestapp.presentation.list.LaunchesListEvent
import com.example.spacextestapp.presentation.list.LaunchesListState
import com.example.spacextestapp.util.Constants.EQUALS_DEFAULT
import com.example.spacextestapp.util.Constants.LESS_DEFAULT
import com.example.spacextestapp.util.Constants.LIMIT
import com.example.spacextestapp.util.Constants.OFFSET
import com.example.spacextestapp.util.Constants.PAGE_SIZE
import com.example.spacextestapp.util.Constants.PATH_DEFAULT
import com.example.spacextestapp.util.Constants.SELECTED_FIELDS_DEFAULT
import com.example.spacextestapp.util.Constants.SELECT_DEFAULT
import com.example.spacextestapp.util.Constants.SORT_DEFAULT
import com.example.spacextestapp.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LaunchesViewModel(private val launchUseCase: LaunchUseCase) : ViewModel() {

    // захардкоженный запрос т.к. не было требования реализации фильтрации или динамичной выборки данных
    private val defaultQuery = LaunchesQuery(
        query = SetupQuery(
            date_utc = QueryRange(
                EQUALS_DEFAULT,
                LESS_DEFAULT
            )
        ),
        options = SetupOptions(
            offset = OFFSET,
            limit = LIMIT,
            pagination = true,
            pageSize = PAGE_SIZE,
            sortBy = SORT_DEFAULT,
            selectedFields = SELECTED_FIELDS_DEFAULT,
            additionalEntity = listOf(
                CrewQuery(
                    path = PATH_DEFAULT,
                    select = SELECT_DEFAULT
                )
            )
        )
    )
    private val _state = MutableStateFlow(LaunchesListState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent: Flow<UiEvent>? = _uiEvent.receiveAsFlow()
    init {
        onListEvent(LaunchesListEvent.GetLaunchesPagingData(defaultQuery))
    }

    fun onListEvent(event: LaunchesListEvent) {
        when (event) {
            is LaunchesListEvent.GetLaunchesPagingData -> {
                _state.value.items = launchUseCase
                    .getLaunchesPagingResult
                    .execute(event.query)
                    .cachedIn(viewModelScope)
            }

            is LaunchesListEvent.OnLaunchesItemClick -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            event.action,
                            event.arguments
                        )
                    )
                }
            }
        }
    }
}