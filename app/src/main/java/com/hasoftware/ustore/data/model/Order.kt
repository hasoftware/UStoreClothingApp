package com.hasoftware.ustore.data.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("id")
    val id: Long? = null,
    
    @SerializedName("userId")
    val userId: String,
    
    @SerializedName("items")
    val items: List<CartItem>,
    
    @SerializedName("totalAmount")
    val totalAmount: Double,
    
    @SerializedName("orderDate")
    val orderDate: String? = null,
    
    @SerializedName("status")
    val status: String = "PENDING",
    
    @SerializedName("shippingAddress")
    val shippingAddress: String? = null,
    
    @SerializedName("paymentMethod")
    val paymentMethod: String? = null,
    
    @SerializedName("notes")
    val notes: String? = null
)
