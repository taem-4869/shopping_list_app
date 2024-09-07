package com.taemallah.shoppinglist.mainActivity.presentation.components

import androidx.compose.ui.graphics.vector.ImageVector
import com.taemallah.shoppinglist.utils.Rout

data class BottomNavigationItem(
    val rout: Rout,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean=false,
    val badgeCount: Int? = null,
)