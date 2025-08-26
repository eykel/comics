package com.example.comics.domain.repository

import com.example.comics.domain.model.Comic

interface ComicRepository {

    suspend fun getComics(): Result<List<Comic>>
}