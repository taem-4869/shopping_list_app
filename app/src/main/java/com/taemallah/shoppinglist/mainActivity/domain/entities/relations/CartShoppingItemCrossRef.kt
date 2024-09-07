package com.taemallah.shoppinglist.mainActivity.domain.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["code","cartId"])
data class CartShoppingItemCrossRef(
    val code: String,
    val cartId: Int,
)
