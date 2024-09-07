package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.presentation

import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.Resource
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.models.ExploreProduct

data class ExploreState(
    val products: Resource<List<ExploreProduct>>? = null,
    val query: String = ""
)