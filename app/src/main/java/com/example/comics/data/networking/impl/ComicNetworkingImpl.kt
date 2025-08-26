package com.example.comics.data.networking.impl

import com.example.comics.data.model.ItemResponse
import com.example.comics.data.networking.ComicNetworking
import com.example.comics.data.service.ComicApi
import com.example.comics.data.util.Authenticator

class ComicNetworkingImpl(
    private val api: ComicApi,
    private val authenticator: Authenticator
) : ComicNetworking  {
    override suspend fun getComics(): ItemResponse {
        val auth = authenticator.createParams()
        return api.getComics(
            auth.ts,
            auth.apikey,
            auth.hash
        )
    }
}