package com.hasoftware.ustore.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import com.hasoftware.ustore.ui.theme.MyApplicationTheme
import com.hasoftware.ustore.ui.theme.BluePrimary
import com.hasoftware.ustore.ui.theme.White
import com.hasoftware.ustore.ui.theme.Black
import com.hasoftware.ustore.ui.animations.*

@Composable
fun StartScreen(
    onGetStartedClick: () -> Unit = {},
    onAlreadyHaveAccountClick: () -> Unit = {}
) {
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(120.dp))
            
            // Logo/Brand name
            Text(
                text = "Stature",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212121)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fadeIn(delay = 200)
                    .padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Subtitle
            Text(
                text = "Style That Empowers.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 19.sp,
                    color = Color(0xFF212121)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fadeIn(delay = 400)
                    .padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Get Started Button
            Button(
                onClick = onGetStartedClick,
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
                )
            ) {
                Text(
                    text = "Let's get started",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = White
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Already have account text
            Row(
                modifier = Modifier
                    .fadeIn(delay = 800)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "I already have an account",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp,
                        color = Color(0xFF212121).copy(alpha = 0.9f)
                    )
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                TextButton(
                    onClick = onAlreadyHaveAccountClick
                ) {
                    Text(
                        text = "Sign In",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = BluePrimary
                        )
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
        
        // Top progress indicator (optional)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp)
                .fadeIn(delay = 100)
        ) {
            Box(
                modifier = Modifier
                    .width(134.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(34.dp))
                    .background(Color(0xFF000000))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    MyApplicationTheme {
        StartScreen()
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun StartScreenPreviewSmall() {
    MyApplicationTheme {
        StartScreen()
    }
}
