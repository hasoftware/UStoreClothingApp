package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.components.StatureBottomNavigation
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.animations.*

data class SearchProduct(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String = ""
)

@Composable
fun SearchScreen(
    onNavigate: (String) -> Unit,
    onProductClick: (String) -> Unit = {},
    currentRoute: String = "search"
) {
    var searchQuery by remember { mutableStateOf("Socks") }
    var isSearchFocused by remember { mutableStateOf(false) }
    
    val searchProducts = remember {
        listOf(
            SearchProduct("1", "Socks X", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
            SearchProduct("2", "Hat Collection", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
            SearchProduct("3", "Sunglasses", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
            SearchProduct("4", "Floral Dress", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
            SearchProduct("5", "Pink Shoes", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
            SearchProduct("6", "Casual Top", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
            SearchProduct("7", "Denim Jacket", "Lorem ipsum dolor sit amet consectetur", "$17,00"),
            SearchProduct("8", "Summer Skirt", "Lorem ipsum dolor sit amet consectetur", "$17,00")
        )
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header section
            item(span = { GridItemSpan(2) }) {
                SearchHeader(
                    searchQuery = searchQuery,
                    onSearchChange = { searchQuery = it },
                    onSearchFocusChange = { isSearchFocused = it },
                    onFilterClick = { /* Handle filter */ }
                )
            }
            
            // Products grid
            items(searchProducts) { product ->
                SearchProductCard(
                    product = product,
                    onClick = { onProductClick(product.id) },
                    modifier = Modifier.fadeIn(delay = (searchProducts.indexOf(product) * 100))
                )
            }
            
            // Bottom spacing for navigation
            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(100.dp))
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
private fun SearchHeader(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
    onFilterClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Title
        Text(
            text = "Shop",
            color = Color(0xFF202020),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fadeIn(delay = 100)
        )
        
        // Search bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search input
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier
                    .weight(1f)
                    .height(36.dp)
                    .fadeIn(delay = 200),
                placeholder = {
                    Text(
                        text = "Search products...",
                        color = Color(0xFF8E8E93),
                        fontSize = 16.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = BluePrimary,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = if (searchQuery.isNotEmpty()) {
                    {
                        IconButton(
                            onClick = { onSearchChange("") }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear",
                                tint = Color(0xFF8E8E93),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                } else null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BluePrimary,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color(0xFFF9F9F9),
                    unfocusedContainerColor = Color(0xFFF9F9F9)
                ),
                shape = RoundedCornerShape(18.dp),
                singleLine = true,
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 16.sp,
                    color = BluePrimary
                )
            )
            
            // Filter button
            IconButton(
                onClick = onFilterClick,
                modifier = Modifier
                    .size(40.dp)
                    .fadeIn(delay = 300)
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter",
                    tint = Color(0xFF202020),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    MaterialTheme {
        SearchScreen(
            onNavigate = {},
            onProductClick = {},
            currentRoute = "search"
        )
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun SearchScreenPreviewSmall() {
    MaterialTheme {
        SearchScreen(
            onNavigate = {},
            onProductClick = {},
            currentRoute = "search"
        )
    }
}
