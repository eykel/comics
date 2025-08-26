package com.example.comics.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    val data : DataResponse
)