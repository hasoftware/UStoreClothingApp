package com.hasoftware.ustore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hasoftware.ustore.ui.components.StatureBottomNavigation
import com.hasoftware.ustore.utils.LanguageManager
import com.hasoftware.ustore.utils.getStringResource
import com.hasoftware.ustore.R

data class SettingsItem(
    val title: String,
    val subtitle: String? = null,
    val onClick: () -> Unit
)

@Composable
fun SettingsScreen(
    onNavigate: (String) -> Unit,
    onProfileClick: () -> Unit,
    onShippingAddressClick: () -> Unit,
    onCountryClick: () -> Unit,
    onCurrencyClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onSizesClick: () -> Unit,
    onTermsClick: () -> Unit,
    currentRoute: String = "profile"
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            SettingsHeader()
            
            // Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            ) {
                // Personal section
                SettingsSection(
                    title = getStringResource(R.string.personal),
                    items = listOf(
                        SettingsItem(getStringResource(R.string.profile_settings), onClick = onProfileClick),
                        SettingsItem(getStringResource(R.string.shipping_address), onClick = onShippingAddressClick)
                    )
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Shop section
                SettingsSection(
                    title = getStringResource(R.string.shop),
                    items = listOf(
                        SettingsItem(getStringResource(R.string.country), getStringResource(R.string.sample_country), onCountryClick),
                        SettingsItem(getStringResource(R.string.currency), LanguageManager.getCurrencyDisplayName("USD"), onCurrencyClick),
                        SettingsItem(getStringResource(R.string.language), LanguageManager.getLanguageDisplayName(LanguageManager.currentLanguage), onLanguageClick),
                        SettingsItem(getStringResource(R.string.sizes), getStringResource(R.string.sample_size), onSizesClick),
                        SettingsItem(getStringResource(R.string.terms_and_conditions), onClick = onTermsClick)
                    )
                )
                
                // Bottom spacing for navigation
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
fun SettingsHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Status bar spacing
        Spacer(modifier = Modifier.height(20.dp))
        
        // Title
        Text(
            text = getStringResource(R.string.settings),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun SettingsSection(
    title: String,
    items: List<SettingsItem>
) {
    Column {
        // Section title
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Section items
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column {
                items.forEachIndexed { index, item ->
                    SettingsItemRow(
                        item = item,
                        showDivider = index < items.size - 1
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsItemRow(
    item: SettingsItem,
    showDivider: Boolean = true
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                
                item.subtitle?.let { subtitle ->
                    Text(
                        text = subtitle,
                        fontSize = 14.sp,
                        color = Color(0xFF8E8E93),
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
            
            IconButton(
                onClick = item.onClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Navigate",
                    tint = Color(0xFF8E8E93),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        
        if (showDivider) {
            HorizontalDivider(
                color = Color(0xFFE5E5EA),
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen(
            onNavigate = {},
            onProfileClick = {},
            onShippingAddressClick = {},
            onCountryClick = {},
            onCurrencyClick = {},
            onLanguageClick = {},
            onSizesClick = {},
            onTermsClick = {}
        )
    }
}

