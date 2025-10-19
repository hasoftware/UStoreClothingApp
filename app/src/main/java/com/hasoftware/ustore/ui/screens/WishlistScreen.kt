package com.hasoftware.ustore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hasoftware.ustore.R
import com.hasoftware.ustore.ui.components.StatureBottomNavigation
import com.hasoftware.ustore.ui.theme.BluePrimary
import com.hasoftware.ustore.ui.theme.White

data class WishlistItem(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val originalPrice: String? = null,
    val isOnSale: Boolean = false,
    val color: String,
    val size: String,
    val imageUrl: String = ""
)

@Composable
fun WishlistScreen(
    onNavigate: (String) -> Unit,
    onProductClick: (String) -> Unit = {},
    currentRoute: String = "wishlist"
) {
    val wishlistItems = remember {
        listOf(
            WishlistItem(
                id = "1",
                name = "Premium T-Shirt",
                description = "Lorem ipsum dolor sit amet consectetur.",
                price = "$17,00",
                color = "Pink",
                size = "M"
            ),
            WishlistItem(
                id = "2",
                name = "Casual Dress",
                description = "Lorem ipsum dolor sit amet consectetur.",
                price = "$12,00",
                originalPrice = "$17,00",
                isOnSale = true,
                color = "Pink",
                size = "M"
            ),
            WishlistItem(
                id = "3",
                name = "Summer Top",
                description = "Lorem ipsum dolor sit amet consectetur.",
                price = "$27,00",
                color = "Pink",
                size = "M"
            ),
            WishlistItem(
                id = "4",
                name = "Elegant Blouse",
                description = "Lorem ipsum dolor sit amet consectetur.",
                price = "$19,00",
                color = "Pink",
                size = "M"
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Status bar spacing
            Spacer(modifier = Modifier.height(16.dp))

            // Header
            Text(
                text = "Wishlist",
                color = Color(0xFF202020),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.28).sp,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            )

            // Wishlist items
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(wishlistItems) { item ->
                    WishlistItemCard(
                        item = item,
                        onProductClick = { onProductClick(item.id) },
                        onRemoveClick = { /* Handle remove from wishlist */ },
                        onAddToCartClick = { /* Handle add to cart */ }
                    )
                }

                // Bottom spacing for navigation
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }

        // Bottom Navigation
        StatureBottomNavigation(
            currentRoute = currentRoute,
            onNavigate = onNavigate,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun WishlistItemCard(
    item: WishlistItem,
    onProductClick: () -> Unit,
    onRemoveClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(123.dp)
            .clickable { onProductClick() },
        shape = RoundedCornerShape(9.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            // Product image
            Box(
                modifier = Modifier
                    .width(121.dp)
                    .height(102.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for product image
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BluePrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "IMG",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Product info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                // Product description
                Text(
                    text = item.description,
                    color = Color.Black,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    maxLines = 2,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Price section
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.price,
                        color = Color(0xFF202020),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.18).sp
                    )

                    if (item.isOnSale && item.originalPrice != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = item.originalPrice,
                            color = Color(0xFFF1AEAE),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Color and size chips
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ColorChip(
                        color = item.color,
                        modifier = Modifier.height(25.dp)
                    )
                    SizeChip(
                        size = item.size,
                        modifier = Modifier.height(25.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Action buttons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    // Remove from wishlist button
                    IconButton(
                        onClick = onRemoveClick,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFF9F9F9))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Remove from wishlist",
                            tint = Color.Red,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Add to cart button
                    IconButton(
                        onClick = onAddToCartClick,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(BluePrimary)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add to cart",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ColorChip(
    color: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(54.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFE5EBFC))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = color,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.14).sp
        )
    }
}

@Composable
private fun SizeChip(
    size: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(50.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFFE5EBFC))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = size,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.14).sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WishlistScreenPreview() {
    MaterialTheme {
        WishlistScreen(
            onNavigate = {},
            onProductClick = {},
            currentRoute = "wishlist"
        )
    }
}

