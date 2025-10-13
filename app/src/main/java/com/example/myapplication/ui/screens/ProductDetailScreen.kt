package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ProductImage(
    val id: String,
    val imageUrl: String = ""
)

data class ProductDetail(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
    val variations: List<String>,
    val images: List<ProductImage>
)

@Composable
fun ProductDetailScreen(
    productId: String,
    onBackClick: () -> Unit,
    onVariationsClick: () -> Unit,
    onAddToCart: () -> Unit,
    onBuyNow: () -> Unit
) {
    val product = remember {
        ProductDetail(
            id = productId,
            name = "Sunglasses & Crop Top",
            price = "$17,00",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam arcu mauris, scelerisque eu mauris id, pretium pulvinar sapien.",
            variations = listOf("Pink", "M"),
            images = listOf(
                ProductImage("1"),
                ProductImage("2"),
                ProductImage("3"),
                ProductImage("4")
            )
        )
    }
    
    var currentImageIndex by remember { mutableStateOf(0) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header with back button
            ProductDetailHeader(
                onBackClick = onBackClick
            )
            
            // Main content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            ) {
                // Product image carousel
                ProductImageCarousel(
                    images = product.images,
                    currentIndex = currentImageIndex,
                    onIndexChange = { currentImageIndex = it }
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Product info
                ProductInfo(
                    product = product,
                    onVariationsClick = onVariationsClick
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Product image thumbnails
                ProductImageThumbnails(
                    images = product.images,
                    currentIndex = currentImageIndex,
                    onImageClick = { currentImageIndex = it }
                )
                
                Spacer(modifier = Modifier.weight(1f))
            }
            
            // Action buttons
            ProductActionButtons(
                onAddToCart = onAddToCart,
                onBuyNow = onBuyNow
            )
        }
    }
}

@Composable
fun ProductDetailHeader(
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Status bar spacing
        Spacer(modifier = Modifier.height(20.dp))
        
        // Back button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.1f))
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun ProductImageCarousel(
    images: List<ProductImage>,
    currentIndex: Int,
    onIndexChange: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        // Main product image placeholder
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            // Placeholder for product image
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF007AFF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "IMG",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
        
        // Carousel indicators
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            images.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == currentIndex) Color(0xFF007AFF) else Color(0xFFE5E5EA)
                        )
                )
            }
        }
    }
}

@Composable
fun ProductInfo(
    product: ProductDetail,
    onVariationsClick: () -> Unit
) {
    Column {
        // Price
        Text(
            text = product.price,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Description
        Text(
            text = product.description,
            fontSize = 14.sp,
            color = Color(0xFF8E8E93),
            lineHeight = 20.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Variations
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Variations",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = product.variations.joinToString(" • "),
                    fontSize = 14.sp,
                    color = Color(0xFF8E8E93)
                )
                
                IconButton(onClick = onVariationsClick) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Show variations",
                        tint = Color(0xFF8E8E93)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductImageThumbnails(
    images: List<ProductImage>,
    currentIndex: Int,
    onImageClick: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(images.size) { index ->
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (index == currentIndex) Color(0xFF007AFF) else Color(0xFFF5F5F5)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${index + 1}",
                    color = if (index == currentIndex) Color.White else Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun ProductActionButtons(
    onAddToCart: () -> Unit,
    onBuyNow: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Add to cart button
        Button(
            onClick = onAddToCart,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Add to cart",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
        
        // Buy now button
        Button(
            onClick = onBuyNow,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007AFF)
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Buy now",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    MaterialTheme {
        ProductDetailScreen(
            productId = "1",
            onBackClick = {},
            onVariationsClick = {},
            onAddToCart = {},
            onBuyNow = {}
        )
    }
}
