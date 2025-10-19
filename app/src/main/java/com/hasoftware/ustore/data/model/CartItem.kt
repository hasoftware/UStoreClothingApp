package com.hasoftware.ustore.data.model

import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("productId")
    val productId: Long,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    
    @SerializedName("price")
    val price: Double,
    
    @SerializedName("quantity")
    val quantity: Int = 1,
    
    @SerializedName("size")
    val size: String? = null,
    
    @SerializedName("color")
    val color: String? = null
)
