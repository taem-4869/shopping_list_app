package com.taemallah.shoppinglist.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class Rout {
    @Serializable
    data object HomeScreen: Rout()
    @Serializable
    data object ExploreScreen: Rout()
}