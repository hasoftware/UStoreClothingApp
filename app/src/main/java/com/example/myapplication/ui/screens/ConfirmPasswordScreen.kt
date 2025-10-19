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
fun ConfirmPasswordScreen(
    onPasswordConfirmed: (String) -> Unit,
    onCancelClick: () -> Unit,
    initialPassword: String = ""
) {
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showPasswordMismatch by remember { mutableStateOf(false) }
    
    // Kiểm tra mật khẩu khớp
    val isPasswordMatch = confirmPassword == initialPassword && confirmPassword.isNotEmpty()
    
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
                text = "Re-enter your password",
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
                value = confirmPassword,
                onValueChange = { 
                    confirmPassword = it
                    showPasswordMismatch = false
                },
                placeholder = "Re-enter your password",
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordVisibilityToggle = { passwordVisible = !passwordVisible },
                showError = showPasswordMismatch,
                modifier = Modifier
                    .fillMaxWidth()
                    .slideInFromBottom(delay = 500)
            )
            
            // Error message
            if (showPasswordMismatch) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Passwords do not match",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 14.sp,
                        color = Color(0xFFFF3838) // Màu đỏ cho lỗi
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fadeIn()
                )
            }
            
            Spacer(modifier = Modifier.height(if (showPasswordMismatch) 40.dp else 56.dp))
            
            // Continue button
            Button(
                onClick = { 
                    if (isPasswordMatch) {
                        onPasswordConfirmed(confirmPassword)
                    } else {
                        showPasswordMismatch = true
                    }
                },
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
                enabled = confirmPassword.isNotEmpty()
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
    onPasswordVisibilityToggle: (() -> Unit)? = null,
    showError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    color = if (showError) Color(0xFFFF3838) else Color(0xFFD1D1D1)
                )
            )
        },
        modifier = modifier.height(52.dp),
        shape = RoundedCornerShape(59.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (showError) Color(0xFFFF3838) else Color.Transparent,
            unfocusedBorderColor = if (showError) Color(0xFFFF3838) else Color.Transparent,
            focusedContainerColor = if (showError) Color(0xFFFFF5F5) else Color(0xFFF7F7F7),
            unfocusedContainerColor = if (showError) Color(0xFFFFF5F5) else Color(0xFFF7F7F7),
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
fun ConfirmPasswordScreenPreview() {
    MyApplicationTheme {
        ConfirmPasswordScreen(
            onPasswordConfirmed = {},
            onCancelClick = {},
            initialPassword = "test123"
        )
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun ConfirmPasswordScreenPreviewSmall() {
    MyApplicationTheme {
        ConfirmPasswordScreen(
            onPasswordConfirmed = {},
            onCancelClick = {},
            initialPassword = "test123"
        )
    }
}
