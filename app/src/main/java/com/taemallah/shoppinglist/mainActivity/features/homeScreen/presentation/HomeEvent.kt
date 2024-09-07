package com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation

import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem

sealed class HomeEvent {
    data class ShowItemDetailsDialog(val shoppingItem: ShoppingItem): HomeEvent()
    data object HideItemDetailsDialog: HomeEvent()
    data class DeleteItem(val shoppingItem: ShoppingItem): HomeEvent()
    data class SetItemPrice(val shoppingItem: ShoppingItem, val newPrice: Float): HomeEvent()
    data object DeleteAll : HomeEvent()
}