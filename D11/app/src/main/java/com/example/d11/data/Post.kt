package com.example.d11.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    @SerialName("closed_captions") val closedCaptions: String? = null
)