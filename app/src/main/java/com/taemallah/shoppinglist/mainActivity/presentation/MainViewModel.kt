package com.taemallah.shoppinglist.mainActivity.presentation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.taemallah.shoppinglist.mainActivity.domain.entities.Cart
import com.taemallah.shoppinglist.mainActivity.domain.repository.ShoppingRepo
import com.taemallah.shoppinglist.mainActivity.presentation.components.BottomNavigationItem
import com.taemallah.shoppinglist.utils.Rout
import com.taemallah.shoppinglist.utils.navOptionsBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: ShoppingRepo
): ViewModel() {
    private lateinit var navController : NavHostController
    private val _homeNavigationItem = MutableStateFlow(
        BottomNavigationItem(
            Rout.HomeScreen,
            "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
            badgeCount = 0
        )
    )
    private val _exploreNavigationItem = MutableStateFlow(
        BottomNavigationItem(
            Rout.ExploreScreen,
            "Explore",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
            hasNews = false,
            badgeCount = null
        )
    )
    private val _state = MutableStateFlow(MainState())
    val state = combine(_homeNavigationItem,_exploreNavigationItem,_state){home,explore,state->
        state.copy(
            navItems = listOf(home,explore)
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainState())

    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.SetBadges -> {
                when(event.rout){
                    Rout.ExploreScreen -> _exploreNavigationItem.update {
                        it.copy(badgeCount = event.count)
                    }
                    Rout.HomeScreen -> _homeNavigationItem.update {
                        it.copy(badgeCount = event.count)
                    }
                }
            }
            is MainEvent.SetHasNews -> {
                when(event.rout){
                    Rout.ExploreScreen -> _exploreNavigationItem.update {
                        it.copy(hasNews = event.hasNews)
                    }
                    Rout.HomeScreen -> _homeNavigationItem.update {
                        it.copy(hasNews = event.hasNews)
                    }
                }
            }
            is MainEvent.SetCurrentScreenIndex -> {
                _state.update {
                    it.copy(currentScreenIndex = event.index)
                }
                navigate(rout= state.value.navItems[event.index].rout)
            }

            is MainEvent.LoadShoppingItemToLocalDatabase -> {
                viewModelScope.launch {
                    repo.upsertShoppingItem(event.shoppingItem).also { Log.e("kid_e","${event.shoppingItem.productName} loaded to database") }
                }
            }

            is MainEvent.CreateNewCart -> {
                viewModelScope.launch {
                    repo.upsertCart(Cart(cartName = event.cartName))
                }
            }

            is MainEvent.AddShoppingItemToCart -> {
                viewModelScope.launch {
                    repo.addItemToCart(event.shoppingItem,event.cart)
                }
            }
        }
    }

    private fun navigate(rout: Rout){
        navController.navigate(rout, navOptions = navOptionsBuilder.build())
    }

    fun setNavController(navHostController: NavHostController){
        try {
            this.navController = navHostController
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}