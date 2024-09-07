package com.taemallah.shoppinglist.mainActivity.domain.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.taemallah.shoppinglist.mainActivity.domain.entities.Cart
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem

data class ShoppingItemWithCarts (
    @Embedded val shoppingItem: ShoppingItem,
    @Relation(
        parentColumn = "code",
        entityColumn = "cartId",
        associateBy = Junction(CartShoppingItemCrossRef::class)
    )
    val carts: List<Cart>
)