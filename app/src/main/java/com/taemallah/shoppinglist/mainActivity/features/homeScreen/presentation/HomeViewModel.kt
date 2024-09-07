package com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taemallah.shoppinglist.mainActivity.domain.repository.ShoppingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ShoppingRepo
): ViewModel() {
    private val _shoppingList = repo.getShoppingItems().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(HomeState())
    val state = combine(_shoppingList,_state){shoppingList, state->
        state.copy(
            shoppingItems = shoppingList
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState())

    fun onEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.DeleteItem -> {
                viewModelScope.launch {
                    repo.deleteShoppingItem(event.shoppingItem)
                }
            }
            HomeEvent.HideItemDetailsDialog -> {
                _state.update {
                    it.copy(currentSelectedItem = null)
                }
            }
            is HomeEvent.ShowItemDetailsDialog -> {
                _state.update {
                    it.copy(currentSelectedItem = event.shoppingItem)
                }
            }

            is HomeEvent.SetItemPrice -> {
                viewModelScope.launch {
                    repo.upsertShoppingItem(event.shoppingItem.copy(price = event.newPrice.toInt()))
                    _state.update {
                        it.copy(currentSelectedItem = null)
                    }
                }
            }

            HomeEvent.DeleteAll -> {
                viewModelScope.launch {
                    repo.deleteShoppingItems()
                }
            }
        }
    }

}