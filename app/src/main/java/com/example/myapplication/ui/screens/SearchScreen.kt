package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items as lazyItems
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

data class SearchHistoryItem(
    val id: String,
    val query: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class DiscoverProduct(
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
    var searchQuery by remember { mutableStateOf("") }
    var isSearchFocused by remember { mutableStateOf(false) }
    
    val searchHistory = remember {
        listOf(
            SearchHistoryItem("1", "Red Dress"),
            SearchHistoryItem("2", "Sunglasses"),
            SearchHistoryItem("3", "Socks"),
            SearchHistoryItem("4", "Mustard Pants"),
            SearchHistoryItem("5", "80-s Skirt")
        )
    }
    
    val discoverProducts = remember {
        listOf(
            DiscoverProduct("1", "Premium Jacket", "Lorem ipsum dolor sit amet consectetur.", "$125,00"),
            DiscoverProduct("2", "Casual Shirt", "Lorem ipsum dolor sit amet consectetur.", "$32,00"),
            DiscoverProduct("3", "Summer Dress", "Lorem ipsum dolor sit amet consectetur.", "$21,00")
        )
    }
    
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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header section
            SearchHeader(
                searchQuery = searchQuery,
                onSearchChange = { searchQuery = it },
                onSearchFocusChange = { isSearchFocused = it },
                onFilterClick = { /* Handle filter */ },
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
            )
            
            if (searchQuery.isEmpty()) {
                // Search History Section
                SearchHistorySection(
                    searchHistory = searchHistory,
                    onHistoryItemClick = { query -> searchQuery = query },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Discover Section
                DiscoverSection(
                    discoverProducts = discoverProducts,
                    onProductClick = onProductClick,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            } else {
                // Search Results
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(searchProducts.filter { it.name.contains(searchQuery, ignoreCase = true) }) { product ->
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
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
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


@Composable
private fun SearchHistorySection(
    searchHistory: List<SearchHistoryItem>,
    onHistoryItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Search history",
            color = Color(0xFF202020),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.18).sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Search history chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            lazyItems(searchHistory) { item ->
                SearchHistoryChip(
                    query = item.query,
                    onClick = { onHistoryItemClick(item.query) }
                )
            }
        }
    }
}

@Composable
private fun SearchHistoryChip(
    query: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(9.dp))
            .background(Color(0xFFF4F4F4))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = query,
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = (-0.17).sp
        )
    }
}

@Composable
private fun DiscoverSection(
    discoverProducts: List<DiscoverProduct>,
    onProductClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Discover",
            color = Color(0xFF202020),
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.21).sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Discover products
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            lazyItems(discoverProducts) { product ->
                DiscoverProductCard(
                    product = product,
                    onClick = { onProductClick(product.id) }
                )
            }
        }
    }
}

@Composable
private fun DiscoverProductCard(
    product: DiscoverProduct,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .height(204.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(9.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Product image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for product image
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(BluePrimary),
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
                    text = product.description,
                    color = Color.Black,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = product.price,
                    color = Color(0xFF202020),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.17).sp
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
