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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.animations.*

@Composable
fun PasswordScreen(
    onPasswordEntered: (String) -> Unit,
    onCancelClick: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    
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
            
            Spacer(modifier = Modifier.height(157.dp))
            
            // Avatar placeholder
            Box(
                modifier = Modifier
                    .size(106.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0))
                    .align(Alignment.CenterHorizontally)
                    .fadeIn(delay = 200)
            ) {
                // Avatar placeholder - có thể thay bằng Image sau
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFBDBDBD))
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Title
            Text(
                text = "Hello, Stacy!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fadeIn(delay = 300)
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Subtitle
            Text(
                text = "Type your password",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 19.sp,
                    color = Color(0xFF212121)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fadeIn(delay = 400)
            )
            
            Spacer(modifier = Modifier.height(46.dp))
            
            // Password form field
            FormField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Enter your password",
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordVisibilityToggle = { passwordVisible = !passwordVisible },
                modifier = Modifier
                    .fillMaxWidth()
                    .slideInFromBottom(delay = 500)
            )
            
            Spacer(modifier = Modifier.height(56.dp))
            
            // Continue button
            Button(
                onClick = { onPasswordEntered(password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(61.dp)
                    .slideInFromBottom(delay = 600),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BluePrimary
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp
                ),
                enabled = password.isNotEmpty()
            ) {
                Text(
                    text = "Continue",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Cancel button
            TextButton(
                onClick = onCancelClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fadeIn(delay = 700)
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
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordVisibilityToggle: (() -> Unit)? = null
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
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = if (isPassword && onPasswordVisibilityToggle != null) {
            {
                TextButton(
                    onClick = onPasswordVisibilityToggle,
                    modifier = Modifier.size(48.dp)
                ) {
                    Text(
                        text = if (passwordVisible) "Hide" else "Show",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 12.sp,
                            color = Color(0xFF8E8E93)
                        )
                    )
                }
            }
        } else null,
        singleLine = true
    )
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

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun PasswordScreenPreviewSmall() {
    MyApplicationTheme {
        PasswordScreen(
            onPasswordEntered = {},
            onCancelClick = {}
        )
    }
}