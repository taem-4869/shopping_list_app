package com.taemallah.shoppinglist.mainActivity.data.database

import androidx.compose.ui.util.fastJoinToString
import androidx.room.TypeConverter

class RoomConverters {

    @TypeConverter
    fun stringListToString(list: List<String>):String{
        return list.fastJoinToString("%*%")
    }

    @TypeConverter
    fun stringToListString(string: String):List<String>{
        return string.split("%*%")
    }

}