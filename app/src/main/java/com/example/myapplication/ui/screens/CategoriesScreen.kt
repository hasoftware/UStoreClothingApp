package com.example.myapplication.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.animations.*
import com.example.myapplication.ui.components.StatureBottomNavigation

data class Category(
    val id: String,
    val name: String,
    val count: Int,
    val imageRes: Int = R.drawable.ic_launcher_foreground // Placeholder image
)

@Composable
fun CategoriesScreen(
    onNavigate: (String) -> Unit,
    onCategoryClick: (String) -> Unit,
    currentRoute: String
) {
    val categories = remember {
        listOf(
            Category("1", "Clothing", 22),
            Category("2", "Shoes", 36),
            Category("3", "Accessories", 18),
            Category("4", "Bags", 14),
            Category("5", "Lingerie", 13),
            Category("6", "Hoodies", 16),
            Category("7", "Jewelry", 8),
            Category("8", "Watches", 12),
            Category("9", "Electronics", 25),
            Category("10", "Home & Garden", 19),
            Category("11", "Sports", 15),
            Category("12", "Books", 7),
            Category("13", "Beauty", 21),
            Category("14", "Health", 9),
            Category("15", "Toys", 11),
            Category("16", "Automotive", 6)
        )
    }

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
                // Title section
                TitleSection()
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            item {
                // Categories grid
                CategoriesGrid(
                    categories = categories,
                    onCategoryClick = onCategoryClick
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
private fun TitleSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp)
    ) {
        Text(
            text = "Categories",
            color = Color(0xFF202020),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fadeIn(delay = 100)
        )
    }
}

@Composable
private fun CategoriesGrid(
    categories: List<Category>,
    onCategoryClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(categories) { category ->
            CategoryCard(
                category = category,
                onClick = { onCategoryClick(category.id) },
                modifier = Modifier
                    .fadeIn(delay = categories.indexOf(category) * 100)
            )
        }
    }
}

@Composable
private fun CategoryCard(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(9.dp)),
        shape = RoundedCornerShape(9.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Category image placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = category.count.toString(),
                color = Color(0xFF202020),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = category.name,
                color = Color(0xFF202020),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    MyApplicationTheme {
        CategoriesScreen(
            onNavigate = {},
            onCategoryClick = {},
            currentRoute = "categories"
        )
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun CategoriesScreenPreviewSmall() {
    MyApplicationTheme {
        CategoriesScreen(
            onNavigate = {},
            onCategoryClick = {},
            currentRoute = "categories"
        )
    }
}
