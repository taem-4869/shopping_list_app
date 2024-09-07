package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.presentation

import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.models.ExploreProduct

sealed class ExploreEvent {
    data class SetQuery(val query: String): ExploreEvent()
    data object Search: ExploreEvent()
    data class LoadProductToLocalDatabase(val product: ExploreProduct): ExploreEvent()
}