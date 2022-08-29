package com.example.spacextestapp.data.remote.request

import com.google.gson.annotations.SerializedName


data class LaunchesQuery(
    val query: SetupQuery,
    val options: SetupOptions
) {
}

data class SetupOptions(
    var offset: Int,
    val limit: Int,
    val pagination: Boolean = true,
    @SerializedName("page")val pageSize: Int = 10,
    @SerializedName("sort")val sortBy: String,
    @SerializedName("select")val selectedFields: String,
    @SerializedName("populate")val additionalEntity: List<PopulatedClass>,
)

abstract class PopulatedClass

data class CrewQuery(
    val path: String,
    val select: String,
): PopulatedClass()


data class SetupQuery(
    val date_utc: QueryRange
)

data class QueryRange(
    @SerializedName("\$gte") val greaterOrEqualsThan: String,
    @SerializedName("\$lte") val lessThan: String
)



