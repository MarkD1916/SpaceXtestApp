package com.example.spacextestapp.domain.model

data class LaunchListData(
    val id: String,
    val launchName: String,
    val flight: Int,
    val launchSuccess: Boolean,
    val date: String, //hh-mm-yyyy
    val imageUrl: String
)
