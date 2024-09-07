package com.taemallah.shoppinglist.mainActivity.domain.entities

import androidx.compose.ui.util.fastJoinToString
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.taemallah.shoppinglist.utils.Constants


@Entity
data class ShoppingItem (
    @PrimaryKey(autoGenerate = false)
    var code: String = "",
    var allergensTags: List<String> = emptyList(),
    var brandsTags: List<String> = emptyList(),
    var categoriesTags: List<String> = emptyList(),
    var foodGroupsTags: List<String> = emptyList(),
    var imageUrl: String = "",
    var ingredientsTags: List<String> = emptyList(),
    var novaGroup: Int = Constants.UNDEFINED_PRODUCT_INT_VALUE,
    var nutriscoreGrade: String = Constants.UNDEFINED_PRODUCT_TEXT_VALUE,
    var nutriscoreScore: Int = Constants.UNDEFINED_PRODUCT_INT_VALUE,
    var productName: String = "",
    var tracesTags: List<String> = emptyList(),

    var price: Int = 0,

){
    fun getItemDetails(): List<ItemDetails> {
        return listOf(
            ItemDetails("Name",productName.ifBlank { "none" }),
            ItemDetails("Price",if (price==0) "none" else "$price$"),
            ItemDetails("Code",code.ifBlank { "no code" }),
            ItemDetails("Brands",brandsTags.fastJoinToString(", ").ifBlank { "none" }),
            ItemDetails("Categories",categoriesTags.fastJoinToString(", ").ifBlank { "none" }),
            ItemDetails("Food groups",foodGroupsTags.fastJoinToString(", ").ifBlank { "none" }),
            ItemDetails("Ingredients",ingredientsTags.fastJoinToString(", ").ifBlank { "none" }),
            ItemDetails("Allergens",allergensTags.fastJoinToString(", ").ifBlank { "none" }),
            ItemDetails("Traces",tracesTags.fastJoinToString(", ").ifBlank { "none" }),
            ItemDetails("Nova Group",if (novaGroup==Constants.UNDEFINED_PRODUCT_INT_VALUE) "none" else novaGroup.toString()),
            ItemDetails("Nutri grade",if (nutriscoreGrade==Constants.UNDEFINED_PRODUCT_TEXT_VALUE) "none" else nutriscoreGrade),
            ItemDetails("Nutri score",if (nutriscoreScore==Constants.UNDEFINED_PRODUCT_INT_VALUE) "none" else nutriscoreScore.toString()),
        )
    }
    data class ItemDetails(val detailName: String, val detailValue: String)
}