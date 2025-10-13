package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ColorOption(
    val id: String,
    val name: String,
    val color: Color,
    val isSelected: Boolean = false
)

data class SizeOption(
    val size: String,
    val isSelected: Boolean = false
)

@Composable
fun ProductVariationsScreen(
    productId: String,
    onAddToCart: () -> Unit,
    onBuyNow: () -> Unit
) {
    val colorOptions = listOf(
        ColorOption("1", "Pink", Color(0xFFFFB6C1), true),
        ColorOption("2", "Blue", Color(0xFF87CEEB)),
        ColorOption("3", "Green", Color(0xFF98FB98)),
        ColorOption("4", "Yellow", Color(0xFFFFFF99)),
        ColorOption("5", "Purple", Color(0xFFDDA0DD))
    )
    
    val sizeOptions = listOf(
        SizeOption("S"),
        SizeOption("M", true),
        SizeOption("L"),
        SizeOption("XL"),
        SizeOption("XXL"),
        SizeOption("XXXL")
    )
    
    var quantity by remember { mutableStateOf(1) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Blurred background image
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = 0.3f }
        ) {
            // Placeholder for blurred product image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
            )
        }
        
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Status bar spacing
            Spacer(modifier = Modifier.height(40.dp))
            
            // Product summary card
            ProductSummaryCard(
                price = "$17,00",
                color = "Pink",
                size = "M"
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Variations content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            ) {
                // Color options
                ColorOptionsSection(
                    colors = colorOptions,
                    onColorSelect = { /* Handle color selection */ }
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Size selection
                SizeSelectionSection(
                    sizes = sizeOptions,
                    onSizeSelect = { /* Handle size selection */ }
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Quantity selector
                QuantitySelector(
                    quantity = quantity,
                    onQuantityChange = { quantity = it }
                )
                
                Spacer(modifier = Modifier.weight(1f))
            }
            
            // Action buttons
            ProductVariationsActionButtons(
                onAddToCart = onAddToCart,
                onBuyNow = onBuyNow
            )
        }
    }
}

@Composable
fun ProductSummaryCard(
    price: String,
    color: String,
    size: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = price,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = color,
                    fontSize = 16.sp,
                    color = Color(0xFF8E8E93)
                )
                
                Text(
                    text = size,
                    fontSize = 16.sp,
                    color = Color(0xFF8E8E93)
                )
            }
        }
    }
}

@Composable
fun ColorOptionsSection(
    colors: List<ColorOption>,
    onColorSelect: (ColorOption) -> Unit
) {
    Column {
        Text(
            text = "Color Options",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(colors) { colorOption ->
                ColorOptionItem(
                    colorOption = colorOption,
                    onClick = { onColorSelect(colorOption) }
                )
            }
        }
    }
}

@Composable
fun ColorOptionItem(
    colorOption: ColorOption,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(60.dp)
    ) {
        // Color circle
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(colorOption.color)
                .then(
                    if (colorOption.isSelected) {
                        Modifier
                    } else {
                        Modifier
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (colorOption.isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        
        // Selection indicator
        if (colorOption.isSelected) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF007AFF).copy(alpha = 0.2f))
            )
        }
    }
}

@Composable
fun SizeSelectionSection(
    sizes: List<SizeOption>,
    onSizeSelect: (SizeOption) -> Unit
) {
    Column {
        Text(
            text = "Size",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            sizes.forEach { sizeOption ->
                SizeOptionButton(
                    sizeOption = sizeOption,
                    onClick = { onSizeSelect(sizeOption) }
                )
            }
        }
    }
}

@Composable
fun SizeOptionButton(
    sizeOption: SizeOption,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(48.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (sizeOption.isSelected) Color(0xFF007AFF) else Color.Transparent
        ),
        shape = RoundedCornerShape(8.dp),
        border = if (!sizeOption.isSelected) {
            androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5E5EA))
        } else null
    ) {
        Text(
            text = sizeOption.size,
            color = if (sizeOption.isSelected) Color.White else Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = "Quantity",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Decrement button
            IconButton(
                onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5))
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Decrease",
                    tint = Color.Black
                )
            }
            
            // Quantity display
            Text(
                text = quantity.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.width(40.dp)
            )
            
            // Increment button
            IconButton(
                onClick = { onQuantityChange(quantity + 1) },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun ProductVariationsActionButtons(
    onAddToCart: () -> Unit,
    onBuyNow: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Add to cart button
        Button(
            onClick = onAddToCart,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Add to cart",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
        
        // Buy now button
        Button(
            onClick = onBuyNow,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007AFF)
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Buy now",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductVariationsScreenPreview() {
    MaterialTheme {
        ProductVariationsScreen(
            productId = "1",
            onAddToCart = {},
            onBuyNow = {}
        )
    }
}
