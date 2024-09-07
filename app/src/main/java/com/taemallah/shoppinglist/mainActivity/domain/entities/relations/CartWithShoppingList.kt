package com.taemallah.shoppinglist.mainActivity.domain.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.taemallah.shoppinglist.mainActivity.domain.entities.Cart
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem

class CartWithShoppingList(
    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "cartId",
        entityColumn = "code",
        associateBy = Junction(CartShoppingItemCrossRef::class)
    )
    val shoppingList: List<ShoppingItem>
)