package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.models

import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem


data class ExploreProduct(
    val allergensTags: List<String>,
    val brandsTags: List<String>,
    val categoriesTags: List<String>,
    val code: String,
    val foodGroupsTags: List<String>,
    val imageUrl: String,
    val ingredientsTags: List<String>,
    val novaGroup: Int,
    val nutriscoreGrade: String,
    val nutriscoreScore: Int,
    val productName: String,
    val tracesTags: List<String>
){
    fun toShoppingItem(): ShoppingItem {
        return ShoppingItem().apply {
            allergensTags = this@ExploreProduct.allergensTags
            brandsTags = this@ExploreProduct.brandsTags
            categoriesTags = this@ExploreProduct.categoriesTags
            code = this@ExploreProduct.code
            foodGroupsTags = this@ExploreProduct.foodGroupsTags
            imageUrl = this@ExploreProduct.imageUrl
            ingredientsTags = this@ExploreProduct.ingredientsTags
            imageUrl = this@ExploreProduct.imageUrl
            ingredientsTags = this@ExploreProduct.ingredientsTags
            novaGroup = this@ExploreProduct.novaGroup
            nutriscoreGrade = this@ExploreProduct.nutriscoreGrade
            nutriscoreScore = this@ExploreProduct.nutriscoreScore
            productName = this@ExploreProduct.productName
            tracesTags = this@ExploreProduct.tracesTags
        }
    }
}