package com.example.comics.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse(
    val results: MutableList<ResultResponse>
)