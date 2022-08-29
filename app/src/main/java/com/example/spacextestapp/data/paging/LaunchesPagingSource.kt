package com.example.spacextestapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.spacextestapp.data.mappers.toDetailLaunchData
import com.example.spacextestapp.data.mappers.toLaunches
import com.example.spacextestapp.data.remote.LaunchesApi
import com.example.spacextestapp.data.remote.request.LaunchesQuery
import com.example.spacextestapp.domain.model.ListAndDetailData
import com.example.spacextestapp.util.Constants.OFFSET
import com.example.spacextestapp.util.Constants.PAGE_SIZE
import javax.inject.Inject

class LaunchesPagingSource @Inject constructor(
    private val mQuery: LaunchesQuery,
    private val launchesApi: LaunchesApi
) : PagingSource<Int, ListAndDetailData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListAndDetailData> {

        val offset = params.key ?: OFFSET
        return try {
            val response = launchesApi.getLaunches(mQuery)
            val pagedResponse = response.body()
            val data = pagedResponse?.launchesData
            val nextPage = pagedResponse?.nextPage

            val listLaunchData = data?.map {
                it.toLaunches()
            } ?: listOf()

            val detailLaunchData = data?.map {
                it.toDetailLaunchData()
            } ?: listOf()

            val launchAndDetailData = (listLaunchData zip detailLaunchData).map {
                ListAndDetailData(
                    list = it.first,
                    detail = it.second
                )
            }
            var nextPageNumber: Int? = null
            if (nextPage != null) {
                nextPageNumber = offset + PAGE_SIZE
                mQuery.options.offset = nextPageNumber
            }

            LoadResult.Page(
                data = launchAndDetailData,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ListAndDetailData>): Int? {

        val anchorPosition = state.anchorPosition ?: return null

        val page = state.closestPageToPosition(anchorPosition) ?: return null

        return page.prevKey?.plus(PAGE_SIZE) ?: page.nextKey?.minus(PAGE_SIZE)
    }


}