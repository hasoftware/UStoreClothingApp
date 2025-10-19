package com.hasoftware.ustore.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Long? = null,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("description")
    val description: String? = null,
    
    @SerializedName("image")
    val image: String? = null,
    
    @SerializedName("parentId")
    val parentId: Long? = null,
    
    @SerializedName("isActive")
    val isActive: Boolean = true,
    
    @SerializedName("sortOrder")
    val sortOrder: Int = 0,
    
    @SerializedName("slug")
    val slug: String? = null,
    
    @SerializedName("metaTitle")
    val metaTitle: String? = null,
    
    @SerializedName("metaDescription")
    val metaDescription: String? = null,
    
    @SerializedName("createdAt")
    val createdAt: String? = null,
    
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)
