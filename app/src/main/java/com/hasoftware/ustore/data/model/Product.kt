package com.hasoftware.ustore.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Long? = null,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("description")
    val description: String? = null,
    
    @SerializedName("price")
    val price: Double,
    
    @SerializedName("originalPrice")
    val originalPrice: Double? = null,
    
    @SerializedName("discountPercentage")
    val discountPercentage: Int = 0,
    
    @SerializedName("brand")
    val brand: String? = null,
    
    @SerializedName("sku")
    val sku: String? = null,
    
    @SerializedName("stockQuantity")
    val stockQuantity: Int = 0,
    
    @SerializedName("minStockLevel")
    val minStockLevel: Int = 0,
    
    @SerializedName("isActive")
    val isActive: Boolean = true,
    
    @SerializedName("isFeatured")
    val isFeatured: Boolean = false,
    
    @SerializedName("isNew")
    val isNew: Boolean = true,
    
    @SerializedName("weight")
    val weight: Double? = null,
    
    @SerializedName("dimensions")
    val dimensions: String? = null,
    
    @SerializedName("color")
    val color: String? = null,
    
    @SerializedName("size")
    val size: String? = null,
    
    @SerializedName("material")
    val material: String? = null,
    
    @SerializedName("warrantyPeriod")
    val warrantyPeriod: String? = null,
    
    @SerializedName("rating")
    val rating: Double = 0.0,
    
    @SerializedName("reviewCount")
    val reviewCount: Int = 0,
    
    @SerializedName("viewCount")
    val viewCount: Int = 0,
    
    @SerializedName("soldCount")
    val soldCount: Int = 0,
    
    @SerializedName("categoryId")
    val categoryId: Long? = null,
    
    @SerializedName("createdAt")
    val createdAt: String? = null,
    
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)
