package com.example.comics.data.mappers

import com.example.comics.data.model.ResultResponse
import com.example.comics.data.util.toUrl
import com.example.comics.domain.model.Comic


fun List<ResultResponse>.toModel(): List<Comic> = this.map {
    it.toComic()
}

fun ResultResponse.toComic(): Comic {
    return Comic(
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        thumbnailUrl = this.thumbnail.toUrl()
    )
}