package com.example.comics.route

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    object HomeScreen: Route()
}