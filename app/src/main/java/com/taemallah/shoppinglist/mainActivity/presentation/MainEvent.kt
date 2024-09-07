package com.taemallah.shoppinglist.mainActivity.presentation

import com.taemallah.shoppinglist.mainActivity.domain.entities.Cart
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem
import com.taemallah.shoppinglist.utils.Rout

sealed class MainEvent {
    data class SetBadges(val rout: Rout,val count: Int): MainEvent()
    data class SetHasNews(val rout: Rout,val hasNews: Boolean): MainEvent()
    data class SetCurrentScreenIndex(val index: Int): MainEvent()
    data class LoadShoppingItemToLocalDatabase(val shoppingItem: ShoppingItem): MainEvent()
    data class CreateNewCart(val cartName: String): MainEvent()
    data class AddShoppingItemToCart(val shoppingItem: ShoppingItem, val cart: Cart): MainEvent()
}