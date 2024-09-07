package com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.taemallah.shoppinglist.R
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem
import com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation.HomeEvent
import com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation.HomeState
import com.taemallah.shoppinglist.mainActivity.presentation.components.LabeledDivider

@Composable
fun ShoppingItemDetailsDialog(modifier: Modifier = Modifier, shoppingItem: ShoppingItem, onEvent:(HomeEvent)->Unit) {
    Dialog(onDismissRequest = { onEvent(HomeEvent.HideItemDetailsDialog) }) {
        OutlinedCard(
            modifier = modifier.fillMaxSize()
        ) {
            var isSettingPrice by remember {
                mutableStateOf(false)
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                item{
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center){
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(shoppingItem.imageUrl.ifBlank { R.drawable.product })
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(id = R.drawable.product),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            alignment = Alignment.Center,
                            clipToBounds = true
                        )
                    }
                }
                items(shoppingItem.getItemDetails()){
                    LabeledDivider(text = it.detailName, textColor = MaterialTheme.colorScheme.primary, dividerColor = MaterialTheme.colorScheme.primary)
                    Text(text = it.detailValue, modifier = Modifier.padding(start = 12.dp))
                }
                item {
                    AnimatedContent(targetState = isSettingPrice) {target->
                        if (!target){
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                OutlinedButton(
                                    modifier = Modifier.fillMaxWidth(.9f),
                                    onClick = { onEvent(HomeEvent.DeleteItem(shoppingItem)) }
                                ) {
                                    Text(text = "Delete")
                                }
                                OutlinedButton(
                                    modifier = Modifier.fillMaxWidth(.9f),
                                    onClick = { isSettingPrice = true }
                                ) {
                                    Text(text = "SetPrice")
                                }
                                TextButton(
                                    modifier = Modifier.fillMaxWidth(.9f),
                                    onClick = { onEvent(HomeEvent.HideItemDetailsDialog) }
                                ) {
                                    Text(text = "Go back")
                                }
                            }
                        }else{
                            var price by rememberSaveable {
                                mutableStateOf("")
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                OutlinedTextField(
                                    value = price,
                                    onValueChange = {
                                        try {
                                            if (it.isBlank()) {
                                                price = ""
                                            }else{
                                                it.toFloat()
                                                price = it
                                            }
                                        }catch (_: Exception){}
                                    },
                                    label = { Text(text = "New price")},
                                    suffix = { Text(text = "$")},
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Go),
                                    keyboardActions = KeyboardActions(onGo = {onEvent(HomeEvent.SetItemPrice(shoppingItem,price.toFloat()))})
                                )
                                Button(
                                    modifier = Modifier.fillMaxWidth(.9f),
                                    onClick = { onEvent(HomeEvent.SetItemPrice(shoppingItem,price.toFloat())) }
                                ) {
                                    Text(text = "Save")
                                }
                                TextButton(
                                    modifier = Modifier.fillMaxWidth(.9f),
                                    onClick = { isSettingPrice=false }
                                ) {
                                    Text(text = "Cancel")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}