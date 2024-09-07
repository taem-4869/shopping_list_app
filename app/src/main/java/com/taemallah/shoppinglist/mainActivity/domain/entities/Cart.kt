package com.taemallah.shoppinglist.mainActivity.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Cart (
    @PrimaryKey
    val cartId: Int? = null,
    var cartName: String = "",
)