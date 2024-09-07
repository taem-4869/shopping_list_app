package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.presentation.components

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.presentation.ExploreEvent
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.presentation.ExploreState
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(modifier: Modifier = Modifier, state: ExploreState, onEvent:(ExploreEvent)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SearchBar(
            query = state.query,
            onQueryChange = { onEvent(ExploreEvent.SetQuery(it)) },
            onSearch = { onEvent(ExploreEvent.Search) },
            active = false,
            onActiveChange = {},
            trailingIcon = {
                AnimatedContent(targetState = state.products, label = "") {
                    when (it){
                        is Resource.Failure -> {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                tint = MaterialTheme.colorScheme.error,
                                contentDescription = null
                            )
                        }
                        Resource.Loading -> {
                            CircularProgressIndicator()
                        }
                        is Resource.Success -> {
                            Text(text = it.result.count().toString())}
                        null -> {Icon(imageVector = Icons.Filled.Search, contentDescription = null)}
                    }
                }
            },
            placeholder = {
                Text(
                    text = "product name, brands, categories ...etc",
                    style = MaterialTheme.typography.labelSmall) },
            modifier = Modifier.fillMaxWidth()
        ) {}
        AnimatedContent(
            targetState = state.products,
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center,
            label = ""
        ) { res ->
            when(res){
                is Resource.Failure -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "Error Occurred:\n${res.exception.cause}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Resource.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }
                is Resource.Success -> {
                    if(res.result.isEmpty()){
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center){
                            Text(
                                text = "Oops, no similar results found!",
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }else{
                        val context = LocalContext.current
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(res.result){
                                ExploreProductItem(
                                    modifier = Modifier.clickable {
                                        onEvent(ExploreEvent.LoadProductToLocalDatabase(it))
                                        Toast.makeText(context,"loading to local database",Toast.LENGTH_LONG).show()
                                    },
                                    product = it
                                )
                            }
                        }
                    }
                }
                null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = "In few Seconds you can find the product you want\nWrite something and search!",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun ExploreScreenPreview() {
    ExploreScreen(state = ExploreState()) {}
}