package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.StatureBottomNavigation

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
    var selectedShipping by remember { mutableStateOf("standard") }
    var selectedPayment by remember { mutableStateOf("card") }
    
    val paymentItems = remember {
        listOf(
            PaymentItem("1", "Lorem ipsum dolor sit amet consectetur.", "$17,00"),
            PaymentItem("2", "Lorem ipsum dolor sit amet consectetur.", "$17,00")
        )
    }
    
    val shippingOptions = remember {
        listOf(
            ShippingOption("standard", "Standard", "5-7 days", "FREE", true),
            ShippingOption("express", "Express", "1-2 days", "$12,00")
        )
    }
    
    val paymentMethods = remember {
        listOf(
            PaymentMethod("card", "Card", true),
            PaymentMethod("cod", "COD")
        )
    }
    
    val totalAmount = paymentItems.sumOf { item ->
        item.price.replace("$", "").replace(",", "").toDoubleOrNull() ?: 0.0
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            PaymentHeader()
            
            // Content
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Shipping Address
                item {
                    ShippingAddressSection(onEditClick = onEditAddressClick)
                }
                
                // Contact Information
                item {
                    ContactInfoSection(onEditClick = onEditContactClick)
                }
                
                // Items
                item {
                    ItemsSection(items = paymentItems)
                }
                
                // Shipping Options
                item {
                    ShippingOptionsSection(
                        options = shippingOptions,
                        selectedOption = selectedShipping,
                        onOptionSelect = { selectedShipping = it }
                    )
                }
                
                // Delivery Info
                item {
                    DeliveryInfoSection()
                }
                
                // Payment Method
                item {
                    PaymentMethodSection(
                        methods = paymentMethods,
                        selectedMethod = selectedPayment,
                        onMethodSelect = { selectedPayment = it },
                        onEditClick = onEditPaymentClick
                    )
                }
                
                // Bottom spacing
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
        
        // Bottom section with total and pay
        PaymentBottomSection(
            totalAmount = totalAmount,
            onPayClick = onPayClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
        
        // Bottom Navigation
        StatureBottomNavigation(
            currentRoute = currentRoute,
            onNavigate = onNavigate,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun PaymentHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // Status bar spacing
        Spacer(modifier = Modifier.height(20.dp))
        
        // Title
        Text(
            text = "Payment",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun ContactInfoSection(onEditClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Contact Information",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF8E8E93)
                )
                
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Contact",
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "+84932000000",
                fontSize = 14.sp,
                color = Color.Black
            )
            
            Text(
                text = "amandamorgan@example.com",
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ItemsSection(items: List<PaymentItem>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Items ${items.size}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            items.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Item thumbnail
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFF5F5F5))
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF007AFF))
                                .align(Alignment.Center),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "P",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        }
                    }
                    
                    // Item info
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = item.name,
                            fontSize = 12.sp,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )
                    }
                    
                    // Item price
                    Text(
                        text = item.price,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun ShippingOptionsSection(
    options: List<ShippingOption>,
    selectedOption: String,
    onOptionSelect: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Shipping Options",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            options.forEach { option ->
                ShippingOptionItem(
                    option = option,
                    isSelected = selectedOption == option.id,
                    onClick = { onOptionSelect(option.id) }
                )
            }
        }
    }
}

@Composable
fun ShippingOptionItem(
    option: ShippingOption,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Radio button
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) Color(0xFF007AFF) else Color(0xFFE5E5EA)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
            
            // Option info
            Column {
                Text(
                    text = option.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text(
                    text = option.duration,
                    fontSize = 12.sp,
                    color = Color(0xFF8E8E93)
                )
            }
        }
        
        // Price
        Text(
            text = option.price,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = if (option.price == "FREE") Color(0xFF007AFF) else Color.Black
        )
    }
}

@Composable
fun DeliveryInfoSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8F4FD)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Text(
            text = "Delivered on or before Thursday, 23 April 2020",
            fontSize = 12.sp,
            color = Color(0xFF007AFF),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun PaymentMethodSection(
    methods: List<PaymentMethod>,
    selectedMethod: String,
    onMethodSelect: (String) -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Payment Method",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Payment",
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                methods.forEach { method ->
                    PaymentMethodButton(
                        method = method,
                        isSelected = selectedMethod == method.id,
                        onClick = { onMethodSelect(method.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun PaymentMethodButton(
    method: PaymentMethod,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF007AFF) else Color(0xFFE5E5EA)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = method.name,
            color = if (isSelected) Color.White else Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun PaymentBottomSection(
    totalAmount: Double,
    onPayClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp)
    ) {
        // Total
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            
            Text(
                text = "$${String.format("%.2f", totalAmount).replace(".", ",")}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Pay button
        Button(
            onClick = onPayClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Pay",
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
        
        // Bottom spacing for navigation
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    MaterialTheme {
        PaymentScreen(
            onNavigate = {},
            onPayClick = {},
            onEditAddressClick = {},
            onEditContactClick = {},
            onEditPaymentClick = {}
        )
    }
}
