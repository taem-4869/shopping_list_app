package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.network

import com.taemallah.shoppinglist.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class ApiService @Inject constructor(
    private val client: HttpClient
) {

    companion object{
        const val END_POINT_PART_1 = "${Constants.BASE_URL}action=process&json=1&search_terms="
        const val END_POINT_PART_2 = "&sort_by=unique_scans_n&page_size=50&fields=product_name,product_name_en,code,nutriscore_score,nutriscore_grade,allergens_tags,brands_tags,categories_tags,food_groups_tags,image_url,ingredients_tags,nova_group,traces_tags"
    }

    suspend fun getProducts(query: String) = client.get(END_POINT_PART_1 + query + END_POINT_PART_2)
}