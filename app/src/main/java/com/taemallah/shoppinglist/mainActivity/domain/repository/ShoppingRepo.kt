package com.taemallah.shoppinglist.mainActivity.domain.repository

import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.taemallah.shoppinglist.mainActivity.domain.entities.Cart
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.CartShoppingItemCrossRef
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.CartWithShoppingList
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.ShoppingItemWithCarts
import kotlinx.coroutines.flow.Flow

interface ShoppingRepo {

    suspend fun upsertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItems()

    suspend fun upsertCart(cart: Cart)

    suspend fun addItemToCart(item: ShoppingItem, cart: Cart)

    suspend fun addItemToCart(code: String, cartId: Int)

    fun getShoppingItems(): Flow<List<ShoppingItem>>

    fun getCartsOfShoppingItem(code: String): Flow<List<ShoppingItemWithCarts>>

    fun getShoppingListOfCart(cartId: Int): Flow<List<CartWithShoppingList>>

}