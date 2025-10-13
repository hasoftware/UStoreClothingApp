package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.BluePrimary
import com.example.myapplication.ui.theme.White
import com.example.myapplication.utils.getStringResource
import com.example.myapplication.R

@Composable
fun StartScreen(
    onGetStartedClick: () -> Unit,
    onAlreadyHaveAccountClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Status bar spacing
            Spacer(modifier = Modifier.height(60.dp))
            
            // Logo and branding
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Logo with shopping bag icon
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(White),
                    contentAlignment = Alignment.Center
                ) {
                    // Shopping bag icon placeholder (you can replace with actual icon)
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(BluePrimary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "S",
                            style = MaterialTheme.typography.headlineLarge,
                            color = White
                        )
                    }
                }
                
                // App title
                Text(
                    text = getStringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                
                // Subtitle
                Text(
                    text = "Style That Empowers.",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
            
            // Bottom section with buttons
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Get Started button
                Button(
                    onClick = onGetStartedClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BluePrimary
                    ),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text(
                        text = getStringResource(R.string.lets_get_started),
                        style = MaterialTheme.typography.labelLarge,
                        color = White
                    )
                }
                
                // Already have account link
                TextButton(
                    onClick = onAlreadyHaveAccountClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "I already have an account",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "→",
                            style = MaterialTheme.typography.bodyMedium,
                            color = BluePrimary
                        )
                    }
                }
            }
            
            // Bottom spacing
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    MyApplicationTheme {
        StartScreen(
            onGetStartedClick = {},
            onAlreadyHaveAccountClick = {}
        )
    }
}
