package com.taemallah.shoppinglist.mainActivity.data.repository

import android.util.Log
import com.taemallah.shoppinglist.mainActivity.data.database.ShoppingDao
import com.taemallah.shoppinglist.mainActivity.domain.entities.Cart
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.CartShoppingItemCrossRef
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.CartWithShoppingList
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.ShoppingItemWithCarts
import com.taemallah.shoppinglist.mainActivity.domain.repository.ShoppingRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingRepoImpl @Inject constructor(
    private val dao: ShoppingDao
): ShoppingRepo {
    override suspend fun upsertShoppingItem(shoppingItem: ShoppingItem) {
        dao.upsertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        dao.deleteShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItems() {
        dao.deleteShoppingItems()
    }

    override suspend fun upsertCart(cart: Cart) {
        dao.upsertCart(cart)
    }

    override suspend fun addItemToCart(item: ShoppingItem, cart: Cart) {
        if(cart.cartId==null){
            Log.e("kid_e","ShoppingRepoImpl: cartId cannot be null")
            return
        }
        dao.upsertCartShoppingItemCrossRef(CartShoppingItemCrossRef(item.code, cart.cartId))
    }

    override suspend fun addItemToCart(code: String, cartId: Int) {
        dao.upsertCartShoppingItemCrossRef(CartShoppingItemCrossRef(code,cartId))
    }

    override fun getShoppingItems(): Flow<List<ShoppingItem>> {
        return dao.getShoppingItems()
    }

    override fun getCartsOfShoppingItem(code: String): Flow<List<ShoppingItemWithCarts>> {
        return dao.getCartsOfShoppingItem(code)
    }

    override fun getShoppingListOfCart(cartId: Int): Flow<List<CartWithShoppingList>> {
        return dao.getShoppingListOfCart(cartId)
    }
}