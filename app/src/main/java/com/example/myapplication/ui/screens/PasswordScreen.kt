package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.BlueLight
import com.example.myapplication.ui.theme.BlueDark
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.theme.GrayLight

@Composable
fun PasswordScreen(
    onPasswordEntered: (String) -> Unit,
    onCancelClick: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        // Background decorative shapes (adjusted for avatar and keyboard)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, end = 40.dp)
        ) {
            // Large blue shape
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(BlueDark, CircleShape)
                    .align(Alignment.TopEnd)
            )
            
            // Medium blue shape
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(BlueLight, CircleShape)
                    .align(Alignment.TopEnd)
                    .padding(top = 40.dp, end = 60.dp)
            )
            
            // Small blue shape at bottom right
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(BlueLight, CircleShape)
                    .align(Alignment.BottomEnd)
                    .padding(end = 20.dp, bottom = 250.dp)
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Status bar spacing
            Spacer(modifier = Modifier.height(60.dp))
            
            // Avatar
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(BlueLight),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for user avatar (you can replace with actual image)
                Text(
                    text = "👩‍💼",
                    style = MaterialTheme.typography.displayLarge
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Greeting
            Text(
                text = "Hello, Stacy!",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Instruction
            Text(
                text = "Type your password",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Password field
            OutlinedTextField(
                value = password,
                onValueChange = { 
                    password = it
                    if (it.isNotEmpty()) {
                        onPasswordEntered(it)
                    }
                },
                label = { Text("Enter your password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BluePrimary,
                    unfocusedBorderColor = GrayLight,
                    focusedLabelColor = BluePrimary
                )
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Cancel button
            TextButton(
                onClick = onCancelClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            
            // Bottom spacing for virtual keyboard
            Spacer(modifier = Modifier.height(320.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordScreenPreview() {
    MyApplicationTheme {
        PasswordScreen(
            onPasswordEntered = {},
            onCancelClick = {}
        )
    }
}
