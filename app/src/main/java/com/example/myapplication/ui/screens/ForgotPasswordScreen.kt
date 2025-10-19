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
fun ForgotPasswordScreen(
    onSendResetLink: (String) -> Unit,
    onBackClick: () -> Unit,
    onMaxAttemptsReached: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var isEmailSent by remember { mutableStateOf(false) }
    
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
                text = "Forgot Password",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                ),
                textAlign = TextAlign.Left,
                modifier = Modifier.fadeIn(delay = 200)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Subtitle
            Text(
                text = if (isEmailSent) {
                    "We've sent a password reset link to your email address. Please check your inbox and follow the instructions to reset your password."
                } else {
                    "Enter your email address and we'll send you a link to reset your password."
                },
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    color = Color(0xFF666666)
                ),
                textAlign = TextAlign.Left,
                modifier = Modifier.fadeIn(delay = 300)
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            if (!isEmailSent) {
                // Email form field
                FormField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "Email address",
                    modifier = Modifier
                        .fillMaxWidth()
                        .slideInFromBottom(delay = 400)
                )
                
                Spacer(modifier = Modifier.height(56.dp))
                
                // Send reset link button
                Button(
                    onClick = { 
                        isEmailSent = true
                        onSendResetLink(email)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(61.dp)
                        .slideInFromBottom(delay = 500),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BluePrimary
                    ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp
                    ),
                    enabled = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                ) {
                    Text(
                        text = "Send Reset Link",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = White
                        )
                    )
                }
            } else {
                // Success state
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .slideInFromBottom(delay = 400),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Success icon placeholder
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .background(Color(0xFF4CAF50))
                            .fadeIn(delay = 500)
                    ) {
                        Text(
                            text = "✓",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontSize = 40.sp,
                                color = White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Text(
                        text = "Email Sent!",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4CAF50)
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                
                Spacer(modifier = Modifier.height(40.dp))
                
                // Resend email button
                OutlinedButton(
                    onClick = { 
                        isEmailSent = false
                        email = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(61.dp)
                        .slideInFromBottom(delay = 600),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = BluePrimary
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        width = 2.dp,
                        color = BluePrimary
                    )
                ) {
                    Text(
                        text = "Send Another Email",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Back button
            TextButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fadeIn(delay = if (isEmailSent) 700 else 600)
            ) {
                Text(
                    text = "Back to Login",
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
fun ForgotPasswordScreenPreview() {
    MyApplicationTheme {
        ForgotPasswordScreen(
            onSendResetLink = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun ForgotPasswordScreenPreviewSmall() {
    MyApplicationTheme {
        ForgotPasswordScreen(
            onSendResetLink = {},
            onBackClick = {}
        )
    }
}
