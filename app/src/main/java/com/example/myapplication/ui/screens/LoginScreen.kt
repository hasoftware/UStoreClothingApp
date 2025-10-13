package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.BlueLight
import com.example.myapplication.ui.theme.BlueDark
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.theme.GrayLight

@Composable
fun LoginScreen(
    onNextClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        // Background decorative shapes (larger than CreateAccount)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, end = 20.dp)
        ) {
            // Large blue shape
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(BlueDark, CircleShape)
                    .align(Alignment.TopEnd)
            )
            
            // Medium blue shape
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(BlueLight, CircleShape)
                    .align(Alignment.TopEnd)
                    .padding(top = 60.dp, end = 80.dp)
            )
            
            // Small blue shape at bottom
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(BlueLight, CircleShape)
                    .align(Alignment.BottomEnd)
                    .padding(end = 40.dp, bottom = 200.dp)
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Status bar spacing
            Spacer(modifier = Modifier.height(60.dp))
            
            // Header
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            // Email field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BluePrimary,
                    unfocusedBorderColor = GrayLight,
                    focusedLabelColor = BluePrimary
                )
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Bottom buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Next button
                Button(
                    onClick = onNextClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BluePrimary
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text(
                        text = "Next",
                        style = MaterialTheme.typography.labelLarge,
                        color = White
                    )
                }
                
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
            }
            
            // Bottom spacing
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MyApplicationTheme {
        LoginScreen(
            onNextClick = {},
            onCancelClick = {}
        )
    }
}
