package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.components.StatureBottomNavigation
import com.example.myapplication.ui.components.EditButton

@Composable
fun EmptyCartScreen(
    onNavigate: (String) -> Unit,
    onCheckoutClick: () -> Unit,
    onEditAddressClick: () -> Unit,
    currentRoute: String = "cart"
) {
    CartEmptyShown(
        modifier = Modifier.fillMaxSize(),
        onNavigate = onNavigate,
        onCheckoutClick = onCheckoutClick,
        onEditAddressClick = onEditAddressClick,
        currentRoute = currentRoute
    )
}

@Composable
fun CartEmptyShown(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
    onCheckoutClick: () -> Unit = {},
    onEditAddressClick: () -> Unit = {},
    currentRoute: String = "cart"
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 812.dp)
            .background(color = Color.White)
    ) {
        // Status bar
        Box(
            modifier = Modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 44.dp)
        ) {
            // Status bar content
            Text(
                text = "9:41",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 21.dp, y = 13.dp)
                    .fillMaxWidth()
                    .requiredHeight(height = 16.dp)
            )
        }
        
        // Title
        Text(
            text = stringResource(id = R.string.cart_empty_title),
            color = Color(0xff202020),
            lineHeight = 1.29.em,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.28).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 48.dp)
        )
        
        // Cart count
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 84.dp, y = 50.dp)
                .requiredSize(size = 30.dp)
        ) {
            Text(
                text = stringResource(id = R.string.cart_count_zero),
                color = Color.Black,
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.18).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .fillMaxWidth()
            )
        }
        
        // Shipping Address
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 95.dp)
                .requiredWidth(width = 335.dp)
                .requiredHeight(height = 70.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0xfff9f9f9))
            )
            Text(
                text = "Shipping Address",
                color = Color(0xff202020),
                lineHeight = 1.29.em,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.14).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 16.dp, y = (-17).dp)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .offset(x = (-16).dp, y = 0.dp)
                    .size(30.dp)
            ) {
                EditButton(
                    onEditClick = onEditAddressClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = "26, Duong So 2, Thao Dien Ward, An Phu, District 2, Ho Chi Minh city",
                color = Color.Black,
                lineHeight = 1.5.em,
                style = TextStyle(fontSize = 10.sp),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 16.dp, y = 11.5.dp)
                    .fillMaxWidth()
                    .requiredHeight(height = 29.dp)
            )
        }
        
        // Empty cart icon
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 121.dp, y = 225.dp)
                .requiredSize(size = 134.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(67.dp))
                    .background(Color(0xFF007AFF))
                    .shadow(elevation = 8.dp)
            )
            Text(
                text = "S",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        
        // Bottom section
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 668.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 60.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xfff9f9f9))
            )
            TextButton(
                onClick = onCheckoutClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(start = 227.dp, top = 10.dp, end = 20.dp, bottom = 10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 128.dp)
                        .requiredHeight(height = 40.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(11.dp))
                            .background(color = Color.White)
                    )
                    Text(
                        text = "Checkout",
                        color = Color(0xff0c0c0c),
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 16.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .fillMaxWidth()
                    )
                }
            }
            Text(
                text = "Total",
                color = Color.Black,
                lineHeight = 1.3.em,
                style = TextStyle(
                    fontSize = 20.sp,
                    letterSpacing = (-0.2).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 20.dp, y = 2.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "$0,00",
                color = Color(0xff202020),
                lineHeight = 1.22.em,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.18).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 20.dp, y = 22.dp)
                    .fillMaxWidth()
            )
        }
        
        // Bottom Navigation
        StatureBottomNavigation(
            currentRoute = currentRoute,
            onNavigate = onNavigate,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun CartEmptyShownPreview() {
    CartEmptyShown(Modifier)
}
