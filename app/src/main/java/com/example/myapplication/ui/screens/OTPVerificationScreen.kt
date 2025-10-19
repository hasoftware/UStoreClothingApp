package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.White
import com.example.myapplication.ui.animations.*

@Composable
fun OTPVerificationScreen(
    onOTPVerified: (String) -> Unit,
    onMaxAttemptsReached: () -> Unit,
    onBackClick: () -> Unit,
    phoneNumber: String = "+84*******00"
) {
    var otp by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(0) }
    val maxAttempts = 3
    
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
                text = "Password Recovery",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202020)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fadeIn(delay = 200)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Subtitle
            Text(
                text = "Enter 4-digits code we sent you on your phone number",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 19.sp,
                    color = Color(0xFF666666)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fadeIn(delay = 300)
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            // Phone number display
            Text(
                text = phoneNumber,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF202020)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fadeIn(delay = 400)
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // OTP input field
            OutlinedTextField(
                value = otp,
                onValueChange = { newValue ->
                    if (newValue.length <= 4 && newValue.all { it.isDigit() }) {
                        otp = newValue
                    }
                },
                placeholder = {
                    Text(
                        text = "Enter 4-digit code",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            color = Color(0xFFD1D1D1)
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .slideInFromBottom(delay = 500),
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
                    fontSize = 18.sp,
                    color = Color(0xFF212121),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(56.dp))
            
            // Verify button
            Button(
                onClick = { 
                    if (otp.length == 4) {
                        // Simulate verification logic
                        if (otp == "1234") {
                            onOTPVerified(otp)
                        } else {
                            attempts++
                            if (attempts >= maxAttempts) {
                                onMaxAttemptsReached()
                            } else {
                                // Show error or try again
                                otp = ""
                            }
                        }
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
                enabled = otp.length == 4
            ) {
                Text(
                    text = "Verify Code",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(18.dp))
            
            // Send again button
            OutlinedButton(
                onClick = { 
                    // Resend OTP logic
                    otp = ""
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .slideInFromBottom(delay = 700),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF202020)
                ),
                border = androidx.compose.foundation.BorderStroke(
                    width = 2.dp,
                    color = Color(0xFF202020)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Send Again",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(18.dp))
            
            // Back button
            TextButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fadeIn(delay = 800)
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
        
        // Progress bar at bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-20).dp)
                .width(134.dp)
                .height(5.dp)
                .clip(RoundedCornerShape(34.dp))
                .background(Color(0xFF000000))
                .fadeIn(delay = 900)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OTPVerificationScreenPreview() {
    MyApplicationTheme {
        OTPVerificationScreen(
            onOTPVerified = {},
            onMaxAttemptsReached = {},
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun OTPVerificationScreenPreviewSmall() {
    MyApplicationTheme {
        OTPVerificationScreen(
            onOTPVerified = {},
            onMaxAttemptsReached = {},
            onBackClick = {}
        )
    }
}
