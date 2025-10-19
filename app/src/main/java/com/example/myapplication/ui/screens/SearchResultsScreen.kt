package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.StatureHeader
import com.example.myapplication.ui.components.StatureBottomNavigation


@Composable
fun SearchResultsScreen(
    searchQuery: String,
    onNavigate: (String) -> Unit,
    onProductClick: (String) -> Unit = {},
    currentRoute: String = "search"
) {
    val searchProducts = listOf(
        SearchProduct("1", "Socks X", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
        SearchProduct("2", "Hat Collection", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
        SearchProduct("3", "Sunglasses", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
        SearchProduct("4", "Floral Dress", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
        SearchProduct("5", "Pink Shoes", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
        SearchProduct("6", "Casual Top", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
        SearchProduct("7", "Denim Jacket", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
        SearchProduct("8", "Summer Skirt", "Lorem ipsum dolor sit amet consectetur", "$17,00")
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header with search
            StatureHeader(
                title = "Search Results",
                showSearch = true,
                searchQuery = searchQuery,
                onSearchChange = { /* Handle search change */ }
            )
            
            // Products grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(searchProducts) { product ->
                    SearchProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) }
                    )
                }
                
                // Bottom spacing for navigation
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
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
fun SearchProductCard(
    product: SearchProduct,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.8f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Product image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for product image
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF007AFF)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "IMG",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
            
            // Product info
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = product.description,
                    fontSize = 12.sp,
                    color = Color(0xFF8E8E93),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = product.price,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultsScreenPreview() {
    MaterialTheme {
        SearchResultsScreen(
            searchQuery = "Socks X",
            onNavigate = {},
            currentRoute = "search"
        )
    }
}
