package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.animations.*

@Composable
fun LoginScreen(
    onNextClick: () -> Unit,
    onCancelClick: () -> Unit,
    onForgotPasswordClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            
            // Progress bar
            Box(
                modifier = Modifier
                    .width(134.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(34.dp))
                    .background(Color(0xFF000000))
                    .fadeIn(delay = 100)
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Title
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                ),
                textAlign = TextAlign.Left,
                modifier = Modifier.fadeIn(delay = 200)
            )
            
            Spacer(modifier = Modifier.height(37.dp))
            
            // Email form field
            FormField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
                modifier = Modifier
                    .fillMaxWidth()
                    .slideInFromBottom(delay = 300)
            )
            
            Spacer(modifier = Modifier.height(56.dp))
            
            // Next button
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(61.dp)
                    .slideInFromBottom(delay = 400),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BluePrimary
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Text(
                    text = "Next",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Forgot password link
            TextButton(
                onClick = onForgotPasswordClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fadeIn(delay = 500)
            ) {
                Text(
                    text = "Forgot your password?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp,
                        color = Color(0xFF212121).copy(alpha = 0.9f)
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Cancel button
            TextButton(
                onClick = onCancelClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fadeIn(delay = 600)
            ) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp,
                        color = Color(0xFF212121).copy(alpha = 0.9f)
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    color = Color(0xFFD1D1D1)
                )
            )
        },
        modifier = modifier.height(52.dp),
        shape = RoundedCornerShape(59.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Color(0xFFF7F7F7),
            unfocusedContainerColor = Color(0xFFF7F7F7),
            focusedTextColor = Color(0xFF212121),
            unfocusedTextColor = Color(0xFF212121)
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontSize = 14.sp,
            color = Color(0xFF212121)
        ),
        singleLine = true
    )
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

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun LoginScreenPreviewSmall() {
    MyApplicationTheme {
        LoginScreen(
            onNextClick = {},
            onCancelClick = {}
        )
    }
}