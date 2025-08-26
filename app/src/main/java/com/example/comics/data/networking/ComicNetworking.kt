package com.example.comics.data.networking

import com.example.comics.data.model.ItemResponse

interface ComicNetworking {

    suspend fun getComics() : ItemResponse
}