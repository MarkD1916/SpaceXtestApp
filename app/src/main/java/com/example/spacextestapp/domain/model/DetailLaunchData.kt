package com.example.spacextestapp.domain.model

import android.os.Parcelable
import com.example.spacextestapp.data.remote.reaponse.entity.Crew
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailLaunchData(
    val launchName: String? = "-",
    val flight: Int? = 0,
    val launchSuccess: Boolean = false,
    val description: String? = "-",
    val date: String? = "-",
    val crew: List<Crew>,
    val imageUrl: String
) : Parcelable
