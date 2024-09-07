package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.repository

import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.Resource
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.network.ApiService
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.network.dto.OpenFoodFactResponse
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.models.ExploreProduct
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.repository.OFFRepo
import io.ktor.client.call.body
import javax.inject.Inject

class OFFRepoImpl @Inject constructor(
    private val apiService: ApiService
): OFFRepo {
    override suspend fun getAPIProducts(query: String): Resource<List<ExploreProduct>> {
        return try {
            Resource.Success(
                apiService.getProducts(query).body<OpenFoodFactResponse>().toProducts()
            )
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}