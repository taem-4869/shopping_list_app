package com.taemallah.shoppinglist.utils

import androidx.navigation.NavOptions

val navOptionsBuilder = NavOptions.Builder()
    .setEnterAnim(android.R.anim.slide_in_left)
    .setExitAnim(android.R.anim.slide_out_right)
    .setPopUpTo<Rout>(route = Rout.HomeScreen,inclusive = false, saveState = false)
