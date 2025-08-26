package com.example.comics.data.service

import com.example.comics.data.model.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApi {

    @GET("comics")
    suspend fun getComics(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ) : ItemResponse
}