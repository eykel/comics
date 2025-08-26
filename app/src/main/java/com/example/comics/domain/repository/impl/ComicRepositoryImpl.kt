package com.example.comics.domain.repository.impl

import com.example.comics.data.mappers.toModel
import com.example.comics.data.networking.ComicNetworking
import com.example.comics.domain.repository.ComicRepository
import com.example.comics.data.util.result
import com.example.comics.domain.model.Comic

class ComicRepositoryImpl(val comicNetworking: ComicNetworking) : ComicRepository {
    override suspend fun getComics(): Result<List<Comic>> =
        result { comicNetworking.getComics().data.results }
            .map { it.toModel() }
}