package com.example.spacextestapp.data.mappers

import com.example.spacextestapp.data.remote.reaponse.entity.LaunchesDto
import com.example.spacextestapp.domain.model.DetailLaunchData
import com.example.spacextestapp.domain.model.LaunchListData
import java.text.SimpleDateFormat
import java.util.*

fun LaunchesDto.toLaunches(): LaunchListData {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return LaunchListData(
        id = id,
        launchName = launchName,
        flight = cores[0].flight,
        launchSuccess = launchSuccess,
        date = dateFormat.format(toMilSec()),
        imageUrl = links.patch.small
    )
}

fun LaunchesDto.toDetailLaunchData(): DetailLaunchData {
    val dateFormat = SimpleDateFormat("HH-mm dd-MM-yy", Locale.getDefault())
    return DetailLaunchData(
        launchName = launchName,
        flight = cores[0].flight,
        launchSuccess = launchSuccess,
        date = dateFormat.format(toMilSec()),
        imageUrl = links.patch.small,
        crew = crew,
        description = description
    )
}

