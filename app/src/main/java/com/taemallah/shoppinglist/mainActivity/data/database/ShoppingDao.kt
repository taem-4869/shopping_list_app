package com.taemallah.shoppinglist.mainActivity.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.taemallah.shoppinglist.mainActivity.domain.entities.Cart
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.CartShoppingItemCrossRef
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.CartWithShoppingList
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.ShoppingItemWithCarts
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Upsert
    suspend fun upsertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("DELETE FROM SHOPPINGITEM")
    suspend fun deleteShoppingItems()

    @Upsert
    suspend fun upsertCart(cart: Cart)

    @Upsert
    suspend fun upsertCartShoppingItemCrossRef(crossRef: CartShoppingItemCrossRef)

    @Transaction
    @Query("SELECT * FROM ShoppingItem")
    fun getShoppingItems(): Flow<List<ShoppingItem>>

    @Transaction
    @Query("SELECT * FROM ShoppingItem WHERE code = :code")
    fun getCartsOfShoppingItem(code: String): Flow<List<ShoppingItemWithCarts>>

    @Transaction
    @Query("SELECT * FROM Cart WHERE cartId = :cartId")
    fun getShoppingListOfCart(cartId: Int): Flow<List<CartWithShoppingList>>

}