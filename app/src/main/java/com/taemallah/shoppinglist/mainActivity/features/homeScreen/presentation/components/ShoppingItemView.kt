package com.taemallah.shoppinglist.mainActivity.features.homeScreen.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastJoinToString
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.taemallah.shoppinglist.R
import com.taemallah.shoppinglist.mainActivity.domain.entities.ShoppingItem

@Composable
fun ShoppingItemView(modifier: Modifier = Modifier, shoppingItem: ShoppingItem) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        border = BorderStroke(
            1.dp,
            Brush.linearGradient(listOf(Color.Transparent,MaterialTheme.colorScheme.primary))
        ),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
        elevation = CardDefaults.outlinedCardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(shoppingItem.imageUrl.ifBlank { R.drawable.product })
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id =R.drawable.product),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                alignment = Alignment.Center,
                clipToBounds = true,
                modifier = Modifier.size(80.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = shoppingItem.productName,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "${shoppingItem.price}$",
                    style = MaterialTheme.typography.labelLarge,
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = shoppingItem.tracesTags.fastJoinToString(", "),
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShoppingItemViewPreveiw() {
    ShoppingItemView(
        shoppingItem = ShoppingItem(
            "1234",
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            "",
            emptyList(),
            0,
            "",
            0,
            "nutella",
            emptyList()
        )
    )
}