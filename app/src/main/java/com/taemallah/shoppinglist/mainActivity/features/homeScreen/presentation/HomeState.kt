package com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation

import com.taemallah.shoppinglist.mainActivity.domain.entities.Cart
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem

data class HomeState(
    val shoppingItems: List<ShoppingItem> = emptyList(),
    val currentSelectedItem: ShoppingItem? = null
)