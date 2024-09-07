package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.repository

import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.Resource
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.models.ExploreProduct

interface OFFRepo {

    suspend fun getAPIProducts(query : String): Resource<List<ExploreProduct>>

}