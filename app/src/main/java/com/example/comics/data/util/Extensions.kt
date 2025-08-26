package com.example.comics.data.util

import com.example.comics.data.model.ThumbnailResponse

inline fun <T> result(
    block: () -> T
) : Result<T> = runCatching {
    Result.success(block())
}.getOrElse {
    Result.failure(it)
}


fun ThumbnailResponse?.toUrl(): String {
    return if (this != null && !this.path.isNullOrEmpty() && !this.extension.isNullOrEmpty()) {
        "${this.path}.${this.extension}"
    } else String().emptyString()
}

fun String.emptyString() = ""