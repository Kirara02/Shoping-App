package com.kirara.sample.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.kirara.core.data.datasource.local.db.entity.ProductEntity
import com.kirara.core.R
import com.kirara.core.ui.theme.Shapes
import com.kirara.core.util.UtilFunctions.fromDollarToRupiah

@Composable
fun ProductCartItem(
    modifier: Modifier = Modifier,
    product: ProductEntity = ProductEntity(),
    onRemoveClicked: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()

    ) {
        Row {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.thumbnail)
                    .crossfade(true)
                    .build(),
                loading = {
                    CircularProgressIndicator(
                        color = Color.LightGray,
                        modifier = Modifier.padding(16.dp)
                    )
                },
                contentDescription = stringResource(R.string.product_thumbnail),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .padding(8.dp)
                    .clip(Shapes.medium)
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1.0f)
            ){
                Text(
                    text = product.title ?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.size(3.dp))
                Text(
                    text = product.price.fromDollarToRupiah(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick =  onRemoveClicked
            ) {
                Icon(Icons.Outlined.Delete,"Delete Icon" )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductCartItemPreview(){
    ProductCartItem(
        modifier = Modifier,
        product = ProductEntity(
            id = 1,
            title = "Product Title",
            price = 100000,
            thumbnail = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
        ),
        onRemoveClicked =  {}
    )
}