package com.example.spacextestapp.data.remote.reaponse.entity

import com.google.gson.annotations.SerializedName

data class LaunchesDataDto(
    @SerializedName("docs")
    val launchesData: List<LaunchesDto>,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean,
    val prevPage: Int?,
    val nextPage: Int?
)