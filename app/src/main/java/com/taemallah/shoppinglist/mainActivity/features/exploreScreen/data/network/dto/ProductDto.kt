package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.network.dto


import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.models.ExploreProduct
import com.taemallah.shoppinglist.utils.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("allergens_tags")
    val allergensTags: List<String>?=null,
    @SerialName("brands_tags")
    val brandsTags: List<String>?=null,
    @SerialName("categories_tags")
    val categoriesTags: List<String>?=null,
    @SerialName("code")
    val code: String?=null,
    @SerialName("food_groups_tags")
    val foodGroupsTags: List<String>?=null,
    @SerialName("image_url")
    val imageUrl: String?=null,
    @SerialName("ingredients_tags")
    val ingredientsTags: List<String>?=null,
    @SerialName("nova_group")
    val novaGroup: Int?=null,
    @SerialName("nutriscore_grade")
    val nutriscoreGrade: String?=null,
    @SerialName("nutriscore_score")
    val nutriscoreScore: Int?=null,
    @SerialName("product_name")
    val productName: String?=null,
    @SerialName("product_name_en")
    val productNameEn: String?=null,
    @SerialName("traces_tags")
    val tracesTags: List<String>?=null
){
    fun toProduct(): ExploreProduct {
        return ExploreProduct(
            allergensTags = allergensTags?: emptyList(),
            brandsTags = brandsTags?: emptyList(),
            categoriesTags = categoriesTags?: emptyList(),
            code = code?:"",
            foodGroupsTags = foodGroupsTags?: emptyList(),
            imageUrl = imageUrl?:"",
            ingredientsTags = ingredientsTags?: emptyList(),
            novaGroup = novaGroup?:-1,
            nutriscoreGrade = nutriscoreGrade?:Constants.UNDEFINED_PRODUCT_TEXT_VALUE,
            nutriscoreScore = nutriscoreScore?:Constants.UNDEFINED_PRODUCT_INT_VALUE,
            productName = if (productNameEn.isNullOrBlank()) productName?:"" else productNameEn,
            tracesTags = tracesTags?: emptyList()
        )
    }
}