package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyCartScreen(
    onNavigate: (String) -> Unit,
    onCheckoutClick: () -> Unit,
    onEditAddressClick: () -> Unit,
    currentRoute: String = "cart"
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
            CartHeader(itemCount = 0)
            
            // Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Shipping Address
                ShippingAddressSection(onEditClick = onEditAddressClick)
                
                // Empty cart icon
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    // Large shopping bag icon
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF007AFF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "S",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 48.sp
                        )
                    }
                }
            }
        }
        
        // Bottom section with total and checkout
        CartBottomSection(
            totalAmount = 0.0,
            onCheckoutClick = onCheckoutClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
       
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyCartScreenPreview() {
    MaterialTheme {
        EmptyCartScreen(
            onNavigate = {},
            onCheckoutClick = {},
            onEditAddressClick = {}
        )
    }
}
