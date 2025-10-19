package com.hasoftware.ustore.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.hasoftware.ustore.R
import com.hasoftware.ustore.ui.components.StatureBottomNavigation
import com.hasoftware.ustore.ui.components.EditButton

data class PaymentItem(
    val id: String,
    val name: String,
    val price: String,
    val imageUrl: String = ""
)

data class ShippingOption(
    val id: String,
    val name: String,
    val duration: String,
    val price: String,
    val isSelected: Boolean = false
)

data class PaymentMethod(
    val id: String,
    val name: String,
    val isSelected: Boolean = false
)

@Composable
fun PaymentScreen(
    onNavigate: (String) -> Unit,
    onPayClick: () -> Unit,
    onEditAddressClick: () -> Unit,
    onEditContactClick: () -> Unit,
    onEditPaymentClick: () -> Unit,
    currentRoute: String = "cart"
) {
    Payment(
        modifier = Modifier.fillMaxSize(),
        onNavigate = onNavigate,
        onPayClick = onPayClick,
        onEditAddressClick = onEditAddressClick,
        onEditContactClick = onEditContactClick,
        onEditPaymentClick = onEditPaymentClick,
        currentRoute = currentRoute
    )
}

@Composable
fun Payment(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
    onPayClick: () -> Unit = {},
    onEditAddressClick: () -> Unit = {},
    onEditContactClick: () -> Unit = {},
    onEditPaymentClick: () -> Unit = {},
    currentRoute: String = "cart"
) {
    Box(
        modifier = modifier
            .requiredWidth(width = 375.dp)
            .requiredHeight(height = 876.dp)
            .background(color = Color.White)
    ) {
        // Status bar
        Box(
            modifier = Modifier
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 44.dp)
        ) {
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
            text = stringResource(id = R.string.payment),
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
                text = stringResource(id = R.string.shipping_address),
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
                text = stringResource(id = R.string.sample_address),
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
        
        // Contact Information
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 171.dp)
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
                text = stringResource(id = R.string.contact_information),
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
            Text(
                text = "${stringResource(id = R.string.sample_phone)}\n${stringResource(id = R.string.sample_email)}",
                color = Color.Black,
                lineHeight = 1.5.em,
                style = TextStyle(fontSize = 10.sp),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 16.dp, y = 10.5.dp)
                    .fillMaxWidth()
                    .requiredHeight(height = 33.dp)
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .offset(x = (-16).dp, y = 0.dp)
                    .size(30.dp)
            ) {
                EditButton(
                    onEditClick = onEditContactClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        
        // Items Section
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 261.dp)
                .requiredWidth(width = 339.dp)
                .requiredHeight(height = 171.dp)
        ) {
            // Items count
            Text(
                text = "2",
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.18).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = (-70.5).dp)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.items),
                color = Color(0xff202020),
                lineHeight = 1.43.em,
                style = TextStyle(
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.21).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = (-66.5).dp)
                    .fillMaxWidth()
            )
            
            // Item 1
            ItemCard(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 0.dp)
                    .requiredWidth(width = 339.dp)
                    .requiredHeight(height = 85.dp),
                itemName = stringResource(id = R.string.sample_product_name),
                itemPrice = stringResource(id = R.string.sample_price),
                itemCount = "1"
            )
            
            // Item 2
            ItemCard(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(x = 0.dp, y = 85.dp)
                    .requiredWidth(width = 339.dp)
                    .requiredHeight(height = 85.dp),
                itemName = stringResource(id = R.string.sample_product_name),
                itemPrice = stringResource(id = R.string.sample_price),
                itemCount = "1"
            )
        }
        
        // Shipping Options
        ShippingOptionsSection(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 454.dp)
                .requiredWidth(width = 335.dp)
                .requiredHeight(height = 152.dp)
        )
        
        // Payment Method
        PaymentMethodSection(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 20.dp, y = 626.dp)
                .requiredWidth(width = 335.dp)
                .requiredHeight(height = 74.dp),
            onEditClick = onEditPaymentClick
        )
        
        // Bottom section
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 732.dp)
                .requiredWidth(width = 375.dp)
                .requiredHeight(height = 60.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 60.dp)
                    .background(color = Color(0xfff9f9f9))
            )
            TextButton(
                onClick = onPayClick,
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
                            .background(color = Color(0xff202020))
                    )
                    Text(
                        text = stringResource(id = R.string.pay),
                        color = Color(0xfff3f3f3),
                        textAlign = TextAlign.Center,
                        lineHeight = 1.56.em,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .fillMaxWidth()
                    )
                }
            }
            Text(
                text = stringResource(id = R.string.total),
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
                text = "$34,00",
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

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    itemName: String,
    itemPrice: String,
    itemCount: String
) {
    Box(
        modifier = modifier
    ) {
        // Product image placeholder
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 5.dp, y = 50.dp)
                .requiredSize(size = 50.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color(0xFF007AFF))
            )
            Text(
                text = "P",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        
        // Item count badge
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 0.dp)
                .requiredSize(size = 20.dp)
                .clip(CircleShape)
                .background(Color(0xFFE5EBFC))
                .border(border = BorderStroke(2.dp, Color.White))
        )
        Text(
            text = itemCount,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 1.31.em,
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.13).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .fillMaxWidth()
        )
        
        // Item name
        Text(
            text = itemName,
            color = Color.Black,
            lineHeight = 1.33.em,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 70.dp, y = 10.dp)
                .fillMaxWidth()
                .requiredHeight(height = 36.dp)
        )
        
        // Item price
        Text(
            text = itemPrice,
            color = Color(0xff202020),
            textAlign = TextAlign.End,
            lineHeight = 1.22.em,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.18).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .offset(x = 0.dp, y = 10.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ShippingOptionsSection(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.shipping_options),
            color = Color(0xff202020),
            lineHeight = 1.43.em,
            style = TextStyle(
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.21).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = (-61).dp)
                .fillMaxWidth()
        )
        
        // Add Voucher button
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .offset(x = 0.dp, y = 0.dp)
                .requiredWidth(width = 120.dp)
                .requiredHeight(height = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(11.dp))
                    .border(border = BorderStroke(1.dp, Color(0xff004bfe)), shape = RoundedCornerShape(11.dp))
            )
            Text(
                text = stringResource(id = R.string.add_voucher),
                color = Color(0xff004bfe),
                textAlign = TextAlign.Center,
                lineHeight = 1.54.em,
                style = TextStyle(fontSize = 13.sp),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth()
            )
        }
        
        // Standard shipping option
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 0.dp)
                .fillMaxWidth()
                .requiredHeight(height = 40.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xffe5ebfc))
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = (-12.5).dp, y = 0.dp)
                .requiredWidth(width = 72.dp)
                .requiredHeight(height = 26.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(color = Color(0xfff5f8ff))
            )
            Text(
                text = stringResource(id = R.string.days_5_7),
                color = Color(0xff004cff),
                textAlign = TextAlign.Center,
                lineHeight = 1.31.em,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = (-0.13).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth()
            )
        }
        Text(
            text = stringResource(id = R.string.standard),
            color = Color.Black,
            lineHeight = 1.25.em,
            style = TextStyle(
                fontSize = 16.sp,
                letterSpacing = (-0.16).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 50.dp, y = 12.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.free),
            color = Color.Black,
            textAlign = TextAlign.End,
            lineHeight = 1.25.em,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.16).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .offset(x = 0.dp, y = 12.dp)
                .fillMaxWidth()
        )
        
        // Express shipping option
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 30.dp)
                .fillMaxWidth()
                .requiredHeight(height = 40.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color(0xfff9f9f9))
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = (-12.5).dp, y = 30.dp)
                .requiredWidth(width = 72.dp)
                .requiredHeight(height = 26.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(color = Color(0xfff5f8ff))
            )
            Text(
                text = stringResource(id = R.string.days_1_2),
                color = Color(0xff004cff),
                textAlign = TextAlign.Center,
                lineHeight = 1.31.em,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = (-0.13).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth()
            )
        }
        Text(
            text = stringResource(id = R.string.express),
            color = Color.Black,
            lineHeight = 1.25.em,
            style = TextStyle(
                fontSize = 16.sp,
                letterSpacing = (-0.16).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 50.dp, y = 42.dp)
                .fillMaxWidth()
        )
        Text(
            text = "$12,00",
            color = Color.Black,
            textAlign = TextAlign.End,
            lineHeight = 1.25.em,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.16).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .offset(x = 0.dp, y = 42.dp)
                .fillMaxWidth()
        )
        
        // Delivery info
        Text(
            text = stringResource(id = R.string.delivered_on),
            color = Color.Black,
            lineHeight = 1.33.em,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 66.dp)
                .fillMaxWidth()
                .requiredHeight(height = 20.dp)
        )
    }
}

@Composable
fun PaymentMethodSection(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
                .offset(x = 0.dp, y = (-22).dp)
                .requiredSize(size = 30.dp)
        ) {
            EditButton(
                onEditClick = onEditClick,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = stringResource(id = R.string.payment_method),
            color = Color(0xff202020),
            lineHeight = 1.43.em,
            style = TextStyle(
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.21).sp
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = (-19).dp)
                .fillMaxWidth()
        )
        
        // COD button
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 22.dp)
                .requiredWidth(width = 73.dp)
                .requiredHeight(height = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(18.dp))
                    .background(color = Color(0xffe5ebfc))
            )
            Text(
                text = stringResource(id = R.string.cod),
                color = Color(0xff004cff),
                textAlign = TextAlign.Center,
                lineHeight = 1.27.em,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.15).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth()
            )
        }
        
        // Card info
        Box(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .offset(x = (-0.5).dp, y = 15.dp)
                .requiredWidth(width = 156.dp)
                .requiredHeight(height = 2.dp)
        ) {
            Text(
                text = stringResource(id = R.string.card_number),
                color = Color(0xff202020),
                lineHeight = 1.29.em,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.14).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 0.dp, y = 8.dp)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.card_masked),
                color = Color(0xff202020),
                lineHeight = 1.33.em,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.12).sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .offset(x = 0.dp, y = 9.dp)
                    .fillMaxWidth()
            )
        }
    }
}


@Preview(widthDp = 375, heightDp = 876)
@Composable
private fun PaymentPreview() {
    Payment(Modifier)
}

