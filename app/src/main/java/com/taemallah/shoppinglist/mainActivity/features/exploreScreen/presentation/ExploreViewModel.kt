package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.Resource
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.models.ExploreProduct
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.repository.OFFRepo
import com.taemallah.shoppinglist.mainActivity.presentation.MainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val offRepo: OFFRepo
) : ViewModel(){
    private val _products = MutableStateFlow<Resource<List<ExploreProduct>>?>(null)
    private val _state = MutableStateFlow(ExploreState())
    val state = combine(_products,_state){products,state->
        state.copy(
            products = products
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ExploreState())
    private var coroutine = CoroutineScope(Dispatchers.Default)

    var onMainEvent:(MainEvent)->Unit = {Log.e("kid_e","onMainEvent() not assigned to ExploreViewModel")}

    fun onEvent(event: ExploreEvent){
        when(event){
            ExploreEvent.Search -> {
                _products.update { Resource.Loading }
                coroutine.cancel()
                coroutine = CoroutineScope(Dispatchers.Default)
                coroutine.launch {
                    _products.update {
                        offRepo.getAPIProducts(_state.value.query)
                    }
                }
            }
            is ExploreEvent.SetQuery -> {
                _state.update {
                    it.copy(
                        query = event.query
                    )
                }
            }

            is ExploreEvent.LoadProductToLocalDatabase -> onMainEvent(MainEvent.LoadShoppingItemToLocalDatabase(event.product.toShoppingItem()))
        }
    }
}