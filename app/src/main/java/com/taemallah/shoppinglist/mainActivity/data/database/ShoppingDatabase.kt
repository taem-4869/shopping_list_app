package com.taemallah.shoppinglist.mainActivity.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.taemallah.shoppinglist.mainActivity.domain.entities.Cart
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem
import com.taemallah.shoppinglist.mainActivity.domain.entities.relations.CartShoppingItemCrossRef

@Database(
    entities = [
        ShoppingItem::class,
        Cart::class,
        CartShoppingItemCrossRef::class
    ],
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class ShoppingDatabase: RoomDatabase(){
    abstract val dao: ShoppingDao
}