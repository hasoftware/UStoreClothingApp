package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.components.StatureBottomNavigation
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.White

data class ProductVariation(
    val id: String,
    val name: String,
    val imageUrl: String = "",
    val isSelected: Boolean = false
)

data class ProductDetail(
    val id: String,
    val name: String,
    val price: String,
    val originalPrice: String? = null,
    val discountPercentage: Int? = null,
    val isOnSale: Boolean = false,
    val description: String,
    val mainImageUrl: String = "",
    val variations: List<ProductVariation> = emptyList(),
    val colors: List<String> = emptyList(),
    val sizes: List<String> = emptyList()
)

@Composable
fun ProductDetailScreen(
    productId: String,
    onNavigate: (String) -> Unit,
    onBackClick: () -> Unit = {},
    currentRoute: String = "product_detail"
) {
    var selectedColor by remember { mutableStateOf("Pink") }
    var selectedSize by remember { mutableStateOf("M") }
    var isLiked by remember { mutableStateOf(false) }
    var selectedImageIndex by remember { mutableStateOf(0) }
    var quantity by remember { mutableStateOf(1) }

    val product = remember {
        ProductDetail(
            id = productId,
            name = "Premium T-Shirt",
            price = "$17,00",
            originalPrice = "$25,00",
            discountPercentage = 32,
            isOnSale = true,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam arcu mauris, scelerisque eu mauris id, pretium pulvinar sapien.",
            mainImageUrl = "",
            variations = listOf(
                ProductVariation("1", "Pink", "", true),
                ProductVariation("2", "Blue", "", false),
                ProductVariation("3", "Green", "", false)
            ),
            colors = listOf("Pink", "Blue", "Green"),
            sizes = listOf("S", "M", "L", "XL", "XXL", "XXXL")
        )
    }

    val productImages = remember {
        listOf(
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Status bar spacing
            Spacer(modifier = Modifier.height(16.dp))

            // Header with back button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF9F9F9))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { isLiked = !isLiked },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF9F9F9))
                ) {
                    Icon(
                        imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isLiked) Color.Red else Color.Black
                    )
                }
            }

            // Product Images
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                // Main product image
                Image(
                    painter = painterResource(id = productImages[selectedImageIndex]),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF5F5F5))
                )

                // Image indicators
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    productImages.forEachIndexed { index, _ ->
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(
                                    if (index == selectedImageIndex) BluePrimary else Color(0xFFE0E0E0)
                                )
                        )
                    }
                }
            }

            // Product Info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // Price with sale
                PriceSection(
                    price = product.price,
                    originalPrice = product.originalPrice,
                    discountPercentage = product.discountPercentage,
                    isOnSale = product.isOnSale
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    text = product.description,
                    color = Color.Black,
                    fontSize = 15.sp,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Variations Section
                VariationsSection(
                    colors = product.colors,
                    sizes = product.sizes,
                    selectedColor = selectedColor,
                    selectedSize = selectedSize,
                    onColorSelected = { selectedColor = it },
                    onSizeSelected = { selectedSize = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Quantity Section
                QuantitySection(
                    quantity = quantity,
                    onQuantityChange = { quantity = it }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Product Images Row
                ProductImagesRow(
                    images = productImages,
                    selectedIndex = selectedImageIndex,
                    onImageSelected = { selectedImageIndex = it }
                )

                Spacer(modifier = Modifier.height(100.dp)) // Bottom spacing for navigation
            }
        }

        // Bottom Action Bar
        BottomActionBar(
            onAddToCart = { /* Handle add to cart */ },
            onBuyNow = { /* Handle buy now */ },
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        // Bottom Navigation
        StatureBottomNavigation(
            currentRoute = currentRoute,
            onNavigate = onNavigate,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun VariationsSection(
    colors: List<String>,
    sizes: List<String>,
    selectedColor: String,
    selectedSize: String,
    onColorSelected: (String) -> Unit,
    onSizeSelected: (String) -> Unit
) {
    Column {
        Text(
            text = "Variations",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.2).sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Colors
        Text(
            text = "Color",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            colors.forEach { color ->
                ColorChip(
                    color = color,
                    isSelected = selectedColor == color,
                    onClick = { onColorSelected(color) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sizes
        Text(
            text = "Size",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            sizes.forEach { size ->
                SizeChip(
                    size = size,
                    isSelected = selectedSize == size,
                    isAvailable = size != "XXL" && size != "XXXL", // Some sizes are disabled
                    onClick = { onSizeSelected(size) }
                )
            }
        }
    }
}

@Composable
private fun ColorChip(
    color: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                when (color.lowercase()) {
                    "pink" -> Color(0xFFFFB6C1)
                    "blue" -> Color(0xFF87CEEB)
                    "green" -> Color(0xFF90EE90)
                    else -> Color(0xFFF5F5F5)
                }
            )
            .clickable { onClick() }
            .then(
                if (isSelected) {
                    Modifier.border(2.dp, BluePrimary, RoundedCornerShape(8.dp))
                } else {
                    Modifier
                }
            )
    )
}

@Composable
private fun SizeChip(
    size: String,
    isSelected: Boolean,
    isAvailable: Boolean = true,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        !isAvailable -> Color(0xFFE5EBFC)
        isSelected -> BluePrimary
        else -> Color(0xFFF9F9F9)
    }
    
    val textColor = when {
        !isAvailable -> Color(0xFFBEC8E5)
        isSelected -> Color.White
        else -> Color.Black
    }
    
    val borderColor = if (isSelected) BluePrimary else Color.Transparent
    
    Box(
        modifier = Modifier
            .width(50.dp)
            .height(25.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .then(
                if (borderColor != Color.Transparent) {
                    Modifier.border(1.dp, borderColor, RoundedCornerShape(4.dp))
                } else {
                    Modifier
                }
            )
            .clickable(enabled = isAvailable) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = size,
            color = textColor,
            fontSize = if (size.length > 3) 13.sp else 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ProductImagesRow(
    images: List<Int>,
    selectedIndex: Int,
    onImageSelected: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images.size) { index ->
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
                    .clickable { onImageSelected(index) }
                    .then(
                        if (index == selectedIndex) {
                            Modifier.border(2.dp, BluePrimary, RoundedCornerShape(8.dp))
                        } else {
                            Modifier
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = images[index]),
                    contentDescription = "Product Image $index",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Composable
private fun PriceSection(
    price: String,
    originalPrice: String?,
    discountPercentage: Int?,
    isOnSale: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = price,
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.26).sp
        )

        if (isOnSale && originalPrice != null) {
            Text(
                text = originalPrice,
                color = Color(0xFF8E8E93),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(4.dp)
                    )
            )

            if (discountPercentage != null) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFE5EBFC))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "-${discountPercentage}%",
                        color = Color(0xFF1A3168),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun QuantitySection(
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Column {
        Text(
            text = "Quantity",
            color = Color(0xFF202020),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.17).sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Decrease button
            IconButton(
                onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF9F9F9))
            ) {
                Text(
                    text = "-",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Quantity display
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(32.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE5EBFC)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = quantity.toString(),
                    color = Color(0xFF1A3168),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Increase button
            IconButton(
                onClick = { onQuantityChange(quantity + 1) },
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE5EBFC))
                    .border(2.dp, BluePrimary, CircleShape)
            ) {
                Text(
                    text = "+",
                    color = BluePrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun BottomActionBar(
    onAddToCart: () -> Unit,
    onBuyNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(84.dp)
            .background(Color.White)
            .shadow(elevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Add to Cart Button
            Button(
                onClick = onAddToCart,
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF202020)
                ),
                shape = RoundedCornerShape(11.dp)
            ) {
                Text(
                    text = "Add to cart",
                    color = Color(0xFFF3F3F3),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            }

            // Buy Now Button
            Button(
                onClick = onBuyNow,
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BluePrimary
                ),
                shape = RoundedCornerShape(11.dp)
            ) {
                Text(
                    text = "Buy now",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }

        // Bottom indicator
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-9).dp)
                .width(134.dp)
                .height(5.dp)
                .clip(RoundedCornerShape(34.dp))
                .background(Color.Black)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    MaterialTheme {
        ProductDetailScreen(
            productId = "1",
            onNavigate = {},
            onBackClick = {},
            currentRoute = "product_detail"
        )
    }
}