package com.example.comics.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    val title: String?,
    val description: String?,
    val thumbnail: ThumbnailResponse?,
)