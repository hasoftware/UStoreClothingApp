package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.utils.getStringResource

@Composable
fun SettingsProfileScreen(
    onNavigate: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveChanges: () -> Unit,
    currentRoute: String = "profile"
) {
    var name by remember { mutableStateOf("Romina") }
    var email by remember { mutableStateOf("gmail@example.com") }
    var password by remember { mutableStateOf("**********") }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header with back button
            SettingsProfileHeader(onBackClick = onBackClick)
            
            // Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Avatar section
                AvatarSection()
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Form fields
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Name field
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF007AFF),
                            unfocusedBorderColor = Color(0xFFE5E5EA),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5)
                        )
                    )
                    
                    // Email field
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF007AFF),
                            unfocusedBorderColor = Color(0xFFE5E5EA),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5)
                        )
                    )
                    
                    // Password field
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF007AFF),
                            unfocusedBorderColor = Color(0xFFE5E5EA),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5)
                        )
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
                        text = getStringResource(R.string.save_changes),
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
                
                // Bottom spacing
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun SettingsProfileHeader(onBackClick: () -> Unit) {
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
                contentDescription = getStringResource(R.string.back),
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
            text = getStringResource(R.string.profile_settings),
            fontSize = 16.sp,
            color = Color(0xFF8E8E93),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 40.dp)
        )
    }
}

@Composable
fun AvatarSection() {
    Box {
        // Main avatar
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFF9C27B0)), // Purple background
            contentAlignment = Alignment.Center
        ) {
            // Placeholder for user avatar
            Text(
                text = "👨‍💼",
                fontSize = 48.sp
            )
        }
        
        // Edit icon
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFF007AFF))
                .align(Alignment.BottomEnd)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = getStringResource(R.string.edit),
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsProfileScreenPreview() {
    MaterialTheme {
        SettingsProfileScreen(
            onNavigate = {},
            onBackClick = {},
            onSaveChanges = {}
        )
    }
}
