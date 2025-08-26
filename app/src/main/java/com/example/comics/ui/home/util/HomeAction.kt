package com.example.comics.ui.home.util

sealed interface HomeAction {
    class Navigate(value: String) : HomeAction
}