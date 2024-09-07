package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.taemallah.shoppinglist.R
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.models.ExploreProduct

@Composable
fun ExploreProductItem(modifier: Modifier = Modifier, product: ExploreProduct) {
    OutlinedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrl.ifBlank { R.drawable.product })
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.product),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                alignment = Alignment.Center,
                clipToBounds = true,
            )
            Text(
                text = product.productName.trim().ifBlank { "-" },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun ExploreProductItemPreview() {
    ExploreProductItem(product = ExploreProduct(
        emptyList(),
        emptyList(),
        emptyList(),
        "1234",
        emptyList(),
        "",
        emptyList(),
        0,
        "",
        0,
        "nutella",
        emptyList()
    ))
}