package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.StatureBottomNavigation
import com.example.myapplication.utils.LanguageManager
import com.example.myapplication.utils.getStringResource
import com.example.myapplication.R

data class LanguageOption(
    val id: String,
    val name: String,
    val isSelected: Boolean = false
)

@Composable
fun LanguageSelectionScreen(
    onNavigate: (String) -> Unit,
    onBackClick: () -> Unit,
    onLanguageSelect: (String) -> Unit,
    currentRoute: String = "profile"
) {
    var selectedLanguage by remember { mutableStateOf(LanguageManager.currentLanguage) }
    
    val languages = listOf(
        LanguageOption("en", LanguageManager.getLanguageNativeName("en"), LanguageManager.currentLanguage == "en"),
        LanguageOption("fr", LanguageManager.getLanguageNativeName("fr"), LanguageManager.currentLanguage == "fr"),
        LanguageOption("ru", LanguageManager.getLanguageNativeName("ru"), LanguageManager.currentLanguage == "ru"),
        LanguageOption("vi", LanguageManager.getLanguageNativeName("vi"), LanguageManager.currentLanguage == "vi")
    )
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header with back button
            LanguageSelectionHeader(onBackClick = onBackClick)
            
            // Content
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(languages) { language ->
                    LanguageOptionItem(
                        language = language,
                        isSelected = selectedLanguage == language.id,
                        onClick = {
                            selectedLanguage = language.id
                            LanguageManager.setLanguage(language.id)
                            onLanguageSelect(language.id)
                        }
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
fun LanguageSelectionHeader(onBackClick: () -> Unit) {
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
        
        // Title
        Text(
            text = getStringResource(R.string.settings),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
        
        // Subtitle
        Text(
            text = getStringResource(R.string.language),
            fontSize = 16.sp,
            color = Color(0xFF8E8E93),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 40.dp)
        )
    }
}

@Composable
fun LanguageOptionItem(
    language: LanguageOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFF007AFF) else Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Language name
            Text(
                text = language.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (isSelected) Color.White else Color.Black
            )
            
            // Selection indicator
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color.White else Color(0xFFFFFFFF)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LanguageSelectionScreenPreview() {
    MaterialTheme {
        LanguageSelectionScreen(
            onNavigate = {},
            onBackClick = {},
            onLanguageSelect = {}
        )
    }
}
