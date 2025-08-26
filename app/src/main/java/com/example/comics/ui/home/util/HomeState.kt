package com.example.comics.ui.home.util

import com.example.comics.domain.model.Comic

sealed class HomeState {
    data class Success(val data: List<Comic>) : HomeState()
    data class Error(val throwable: Throwable) : HomeState()
    object Loading : HomeState()
}