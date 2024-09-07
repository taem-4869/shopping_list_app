package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.network.dto


import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.models.ExploreProduct
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenFoodFactResponse(
    @SerialName("count")
    val count: String? = null,
    @SerialName("page")
    val page: String? = null,
    @SerialName("page_count")
    val pageCount: Int? = null,
    @SerialName("page_size")
    val pageSize: Int? = null,
    @SerialName("products")
    val products: List<ProductDto>? = null,
    @SerialName("skip")
    val skip: Int? = null
){
    fun toProducts():List<ExploreProduct>{
        return products?.map { it.toProduct() }?: emptyList()
    }
}