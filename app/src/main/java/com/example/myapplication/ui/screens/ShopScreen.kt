package com.example.myapplication.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.animations.*
import com.example.myapplication.ui.components.StatureBottomNavigation

@Composable
fun ShopScreen(
    onNavigate: (String) -> Unit,
    currentRoute: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            item {
                // Search bar
                SearchBar(
                    onSearchClick = { onNavigate("search") }
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            item {
                // Big Sale Banner
                BigSaleBanner()
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            item {
            // Categories section
            CategoriesSection(
                onCategoryClick = { category ->
                    // Handle category click
                },
                onSeeAllClick = {
                    onNavigate("categories")
                }
            )
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            item {
                // Sale Now section
                SaleNowSection(
                    onProductClick = { productId ->
                        // Handle product click
                    },
                    onSeeAllClick = {
                        onNavigate("search")
                    }
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            item {
                // Just For You section
                JustForYouSection(
                    onProductClick = { productId ->
                        // Handle product click
                    }
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(100.dp)) // Space for bottom navigation
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
private fun SearchBar(
    onSearchClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
        ) {
            // Title
            Text(
                text = "Shop",
                color = Color(0xFF202020),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fadeIn(delay = 100)
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Search bar
            Box(
                modifier = Modifier
                    .width(248.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFFF8F8F8))
                    .clickable { onSearchClick() }
                    .fadeIn(delay = 200)
            ) {
                Text(
                    text = "Search",
                    color = Color(0xFFC7C7C7),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun BigSaleBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 21.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0042E0),
                        Color(0xFF0056FF),
                        Color(0xFF006AFF)
                    )
                )
            )
            .slideInFromBottom(delay = 300)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 24.dp)
        ) {
            Text(
                text = "Big Sale",
                color = White,
                fontSize = 29.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fadeIn(delay = 400)
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "Up to 50%",
                color = White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fadeIn(delay = 500)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Happening\nNow",
                color = White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 15.sp,
                modifier = Modifier.fadeIn(delay = 600)
            )
        }
        
        // Page indicators
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-20).dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
            )
            
            repeat(3) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                )
            }
        }
    }
}

@Composable
private fun CategoriesSection(
    onCategoryClick: (String) -> Unit,
    onSeeAllClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categories",
                color = Color(0xFF202020),
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fadeIn(delay = 100)
            )
            
            Text(
                text = "See All",
                color = Color(0xFF202020),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fadeIn(delay = 200)
                    .clickable { onSeeAllClick() }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Categories grid
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(9.dp))
                .background(White)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(9.dp))
                .padding(16.dp)
                .slideInFromBottom(delay = 300)
        ) {
            // First row of categories
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryItem(
                    title = "Clothing",
                    count = "22",
                    modifier = Modifier.weight(1f)
                )
                CategoryItem(
                    title = "Shoes",
                    count = "36",
                    modifier = Modifier.weight(1f)
                )
                CategoryItem(
                    title = "Accessories",
                    count = "18",
                    modifier = Modifier.weight(1f)
                )
                CategoryItem(
                    title = "Bags",
                    count = "14",
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Second row of categories
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryItem(
                    title = "Lingerie",
                    count = "13",
                    modifier = Modifier.weight(1f)
                )
                CategoryItem(
                    title = "Hoodies",
                    count = "16",
                    modifier = Modifier.weight(1f)
                )
                CategoryItem(
                    title = "Jewelry",
                    count = "8",
                    modifier = Modifier.weight(1f)
                )
                CategoryItem(
                    title = "Watches",
                    count = "12",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun CategoryItem(
    title: String,
    count: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Category image placeholder
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color(0xFFF5F5F5))
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = count,
            color = Color(0xFF202020),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        
        Text(
            text = title,
            color = Color(0xFF202020),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SaleNowSection(
    onProductClick: (String) -> Unit,
    onSeeAllClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sale Now",
                color = Color(0xFF202020),
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fadeIn(delay = 100)
            )
            
            Text(
                text = "See All",
                color = Color(0xFF202020),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fadeIn(delay = 200)
                    .clickable { onSeeAllClick() }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Products grid
        Column(
            modifier = Modifier.slideInFromBottom(delay = 300)
        ) {
            // First row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProductCard(
                    title = "Summer Dress",
                    price = "$29.99",
                    discount = "-20%",
                    modifier = Modifier.weight(1f)
                )
                ProductCard(
                    title = "Casual Shirt",
                    price = "$24.99",
                    discount = "-15%",
                    modifier = Modifier.weight(1f)
                )
                ProductCard(
                    title = "Jeans",
                    price = "$39.99",
                    discount = "-25%",
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Second row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProductCard(
                    title = "T-Shirt",
                    price = "$19.99",
                    discount = "-10%",
                    modifier = Modifier.weight(1f)
                )
                ProductCard(
                    title = "Sneakers",
                    price = "$79.99",
                    discount = "-30%",
                    modifier = Modifier.weight(1f)
                )
                ProductCard(
                    title = "Jacket",
                    price = "$89.99",
                    discount = "-20%",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun JustForYouSection(
    onProductClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp)
    ) {
        Text(
            text = "Just For You",
            color = Color(0xFF202020),
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fadeIn(delay = 100)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Products grid
        Column(
            modifier = Modifier.slideInFromBottom(delay = 200)
        ) {
            // First row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProductCard(
                    title = "Designer Bag",
                    price = "$129.99",
                    modifier = Modifier.weight(1f)
                )
                ProductCard(
                    title = "Elegant Watch",
                    price = "$199.99",
                    modifier = Modifier.weight(1f)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Second row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProductCard(
                    title = "Luxury Shoes",
                    price = "$149.99",
                    modifier = Modifier.weight(1f)
                )
                ProductCard(
                    title = "Premium Hat",
                    price = "$49.99",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ProductCard(
    title: String,
    price: String,
    discount: String? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(9.dp))
            .background(White)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(9.dp))
            .padding(8.dp)
    ) {
        Column {
            // Product image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(103.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xFFF5F5F5))
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = title,
                color = Color.Black,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = price,
                color = Color(0xFF202020),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        // Discount badge
        if (discount != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFFFF5790), Color(0xFFF81140))
                        )
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = discount,
                    color = White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShopScreenPreview() {
    MyApplicationTheme {
        ShopScreen(
            onNavigate = {},
            currentRoute = "shop"
        )
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun ShopScreenPreviewSmall() {
    MyApplicationTheme {
        ShopScreen(
            onNavigate = {},
            currentRoute = "shop"
        )
    }
}