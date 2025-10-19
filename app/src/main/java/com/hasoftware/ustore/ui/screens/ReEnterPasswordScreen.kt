package com.hasoftware.ustore.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import com.hasoftware.ustore.ui.animations.fadeIn
import com.hasoftware.ustore.ui.animations.slideInFromBottom
import com.hasoftware.ustore.ui.theme.BlueDark
import com.hasoftware.ustore.ui.theme.BlueLight
import com.hasoftware.ustore.ui.theme.MyApplicationTheme
import com.hasoftware.ustore.ui.theme.White

@Composable
fun ReEnterPasswordScreen(
    onPasswordConfirmed: (String) -> Unit,
    onCancelClick: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        // Background decorative shapes
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
                    .padding(top = 20.dp, end = 40.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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

            Spacer(modifier = Modifier.height(100.dp))

            // Avatar placeholder
            Box(
                modifier = Modifier
                    .size(106.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE0E0E0)) // Placeholder color
                    .fadeIn(delay = 200),
                contentAlignment = Alignment.Center
            ) {
                // You can add an Image here if you have an avatar asset
                Text(
                    text = "👤", // Placeholder for avatar
                    fontSize = 48.sp,
                    textAlign = TextAlign.Center
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
                modifier = Modifier.fadeIn(delay = 300)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle
            Text(
                text = "Re-enter your password",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 19.sp,
                    color = Color(0xFF212121)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fadeIn(delay = 400)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Password Form field
            FormField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Re-enter your password",
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
                onClick = { onPasswordConfirmed(password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(61.dp)
                    .slideInFromBottom(delay = 600),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007AFF) // BluePrimary
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

            Spacer(modifier = Modifier.height(18.dp))

            // Forgot password link
            TextButton(
                onClick = { /* Handle forgot password */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fadeIn(delay = 700)
            ) {
                Text(
                    text = "Forgot your password?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp,
                        color = Color(0xFF212121).copy(alpha = 0.9f)
                    )
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Cancel button
            TextButton(
                onClick = onCancelClick,
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
    }
}

// Reusing FormField from other screens for consistency
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
                IconButton(
                    onClick = onPasswordVisibilityToggle,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        tint = Color(0xFF8E8E93),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        } else null,
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun ReEnterPasswordScreenPreview() {
    MyApplicationTheme {
        ReEnterPasswordScreen(onPasswordConfirmed = {}, onCancelClick = {})
    }
}

