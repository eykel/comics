package com.example.comics.data.model

import kotlinx.serialization.Serializable


@Serializable
data class ThumbnailResponse(
    val path: String?,
    val extension: String?
)