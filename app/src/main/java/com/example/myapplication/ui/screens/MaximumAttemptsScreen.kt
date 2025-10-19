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
import androidx.compose.ui.draw.shadow
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
fun MaximumAttemptsScreen(
    onOkayClick: () -> Unit,
    onSendAgainClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E0E0E).copy(alpha = 0.78f))
    ) {
        // Main dialog container
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .width(347.dp)
                .height(263.dp)
                .clip(RoundedCornerShape(19.dp))
                .background(Color(0xFFF8F8F8))
                .fadeIn(delay = 100)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                
                // Error icon
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF3838))
                        .shadow(elevation = 8.dp)
                        .fadeIn(delay = 200)
                ) {
                    // Error icon content (X mark)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        // Vertical line
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height(40.dp)
                                .background(Color.White)
                                .clip(RoundedCornerShape(2.dp))
                                .align(Alignment.Center)
                        )
                        // Horizontal line
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(3.dp)
                                .background(Color.White)
                                .clip(RoundedCornerShape(2.dp))
                                .align(Alignment.Center)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Title
                Text(
                    text = "Maximum Attempts Reached",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF202020)
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fadeIn(delay = 300)
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Message
                Text(
                    text = "You have reached the maximum number of attempts. Please try again later or contact support.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        color = Color(0xFF666666),
                        lineHeight = 20.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fadeIn(delay = 400)
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Okay button
                Button(
                    onClick = onOkayClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .slideInFromBottom(delay = 500),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF202020)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Okay",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFF3F3F3)
                        )
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Send Again button
                OutlinedButton(
                    onClick = onSendAgainClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .slideInFromBottom(delay = 600),
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
                
                Spacer(modifier = Modifier.height(20.dp))
                
                // Cancel button
                TextButton(
                    onClick = onCancelClick,
                    modifier = Modifier
                        .fadeIn(delay = 700)
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 15.sp,
                            color = Color(0xFF202020).copy(alpha = 0.9f)
                        )
                    )
                }
            }
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
                .fadeIn(delay = 800)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MaximumAttemptsScreenPreview() {
    MyApplicationTheme {
        MaximumAttemptsScreen(
            onOkayClick = {},
            onSendAgainClick = {},
            onCancelClick = {}
        )
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun MaximumAttemptsScreenPreviewSmall() {
    MyApplicationTheme {
        MaximumAttemptsScreen(
            onOkayClick = {},
            onSendAgainClick = {},
            onCancelClick = {}
        )
    }
}
