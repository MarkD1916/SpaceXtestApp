package com.example.spacextestapp.data.remote

import com.example.spacextestapp.data.remote.reaponse.entity.LaunchesDataDto
import com.example.spacextestapp.data.remote.request.LaunchesQuery
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LaunchesApi {

    @POST("launches/query")
    suspend fun getLaunches(
        @Body launchesQuery: LaunchesQuery
    ): Response<LaunchesDataDto>

}