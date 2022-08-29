package com.example.spacextestapp.data.remote.reaponse.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class LaunchesDto(
    val id: String,
    @SerializedName("success") val launchSuccess: Boolean,
    @SerializedName("details") val description: String,
    val crew: List<Crew>,
    @SerializedName("name") val launchName: String,
    @SerializedName("date_unix") val date: Long,
    val cores: List<CoresDto>,
    val links: Links
) {
    fun toMilSec(): Long {
        return date * 1000L
    }
}

@Parcelize
data class Crew(
    val name: String,
    val agency: String,
    val status: String,
    val id: String
): Parcelable


data class Links(
    val patch: Patch
)

data class Patch(
    val small: String,
    val large: String
)

data class CoresDto(
    val flight: Int
)