package com.taemallah.shoppinglist.mainActivity.presentation

import com.taemallah.shoppinglist.mainActivity.presentation.components.BottomNavigationItem

data class MainState (
    val navItems: List<BottomNavigationItem> = emptyList(),
    val currentScreenIndex: Int = 0
)