package com.example.comics.repository

import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date

class Repository {

    suspend fun getComics() = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com/v1/public/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java).getComics(
            apikey = Authentication().publicKey,
            ts = Authentication().ts,
            hash = Authentication().hash
        ).await()

}