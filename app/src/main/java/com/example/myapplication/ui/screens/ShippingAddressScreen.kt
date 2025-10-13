package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.StatureBottomNavigation

@Composable
fun ShippingAddressScreen(
    onNavigate: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveChanges: () -> Unit,
    onEditAddress: () -> Unit = {},
    currentRoute: String = "profile"
) {
    var address by remember { mutableStateOf("") }
    var townCity by remember { mutableStateOf("") }
    var postcode by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header with back button
            ShippingAddressHeader(onBackClick = onBackClick)
            
            // Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            ) {
                // Form fields
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Address field
                    AddressField(
                        label = "Address",
                        value = address,
                        onValueChange = { address = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    // Town / City field
                    AddressField(
                        label = "Town / City",
                        value = townCity,
                        onValueChange = { townCity = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    // Postcode field
                    AddressField(
                        label = "Postcode",
                        value = postcode,
                        onValueChange = { postcode = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    // Phone Number field
                    AddressField(
                        label = "Phone Number",
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        keyboardType = KeyboardType.Phone,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Save Changes button
                Button(
                    onClick = onSaveChanges,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF007AFF)
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text(
                        text = "Save Changes",
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
                
                // Edit Address button (if data exists)
                if (address.isNotEmpty() || townCity.isNotEmpty() || postcode.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedButton(
                        onClick = onEditAddress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFF007AFF)
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF007AFF)),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Text(
                            text = "Edit Address",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }
                }
                
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
fun ShippingAddressHeader(onBackClick: () -> Unit) {
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
            text = "Settings",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
        
        // Subtitle
        Text(
            text = "Shipping Address",
            fontSize = 16.sp,
            color = Color(0xFF8E8E93),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 40.dp)
        )
    }
}

@Composable
fun AddressField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(
        modifier = modifier
    ) {
        // Label
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Input field
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Required") },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF007AFF),
                unfocusedBorderColor = Color(0xFFE5E5EA),
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShippingAddressScreenPreview() {
    MaterialTheme {
        ShippingAddressScreen(
            onNavigate = {},
            onBackClick = {},
            onSaveChanges = {}
        )
    }
}
