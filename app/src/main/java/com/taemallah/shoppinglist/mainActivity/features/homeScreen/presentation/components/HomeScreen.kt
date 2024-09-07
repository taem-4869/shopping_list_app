package com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation.HomeEvent
import com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation.HomeState
import com.taemallah.shoppinglist.mainActivity.presentation.components.LabeledDivider

@Composable
fun HomeScreen(modifier: Modifier = Modifier, state: HomeState, onEvent:(HomeEvent)->Unit) {
    var isClearingShoppingList by remember {
        mutableStateOf(false)
    }
    if (isClearingShoppingList){
        AlertDialog(
            dismissButton = { TextButton(onClick = { isClearingShoppingList=false }) {
                Text(text = "Cancel")
            }},
            onDismissRequest = { isClearingShoppingList=false },
            confirmButton = {
                Button(onClick = {
                    onEvent(HomeEvent.DeleteAll)
                    isClearingShoppingList = false
                }) {
                    Text(text = "Delete All")
                }
            },
            text = { Text(text = "Are you sure you want to clear all Shopping items?") }
        )
    }
    if (state.currentSelectedItem!=null){
        ShoppingItemDetailsDialog(shoppingItem = state.currentSelectedItem, onEvent = onEvent)
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "shopping cart")
                Text(
                    text = "My Shopping List",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = { isClearingShoppingList = true }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete all")
                }
            }
        }
        if (state.shoppingItems.isEmpty()){
            item {
                Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center){
                    Text(text = "no items found")
                }
            }
        }else
        items(state.shoppingItems,){
            ShoppingItemView(
                modifier = Modifier
                    .clickable { onEvent(HomeEvent.ShowItemDetailsDialog(shoppingItem = it)) },
                shoppingItem = it
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}