package com.hasoftware.ustore.data.api

import com.hasoftware.ustore.data.model.Category
import com.hasoftware.ustore.data.model.Product
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    // Authentication
    @POST("auth/signin")
    suspend fun signIn(@Body loginRequest: LoginRequest): Response<JwtResponse>
    
    @POST("auth/signup")
    suspend fun signUp(@Body signupRequest: SignupRequest): Response<MessageResponse>
    
    // Products
    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ProductsResponse>
    
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Long): Response<Product>
    
    @GET("products/search")
    suspend fun searchProducts(
        @Query("keyword") keyword: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ProductsResponse>
    
    @GET("products/category/{categoryId}")
    suspend fun getProductsByCategory(
        @Path("categoryId") categoryId: Long,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ProductsResponse>
    
    @GET("products/featured")
    suspend fun getFeaturedProducts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ProductsResponse>
    
    @GET("products/new")
    suspend fun getNewProducts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<ProductsResponse>
    
    // Categories
    @GET("categories")
    suspend fun getCategories(): Response<List<Category>>
    
    @GET("categories/active")
    suspend fun getActiveCategories(): Response<List<Category>>
    
    @GET("categories/{id}")
    suspend fun getCategoryById(@Path("id") id: Long): Response<Category>
}

// Request/Response DTOs
data class LoginRequest(
    val username: String,
    val password: String
)

data class SignupRequest(
    val username: String,
    val email: String,
    val password: String,
    val fullName: String? = null,
    val phone: String? = null
)

data class JwtResponse(
    val accessToken: String,
    val tokenType: String = "Bearer",
    val id: Long,
    val username: String,
    val email: String,
    val roles: List<String>
)

data class MessageResponse(
    val message: String
)

data class ProductsResponse(
    val content: List<Product>,
    val totalElements: Long,
    val totalPages: Int,
    val size: Int,
    val number: Int
)
