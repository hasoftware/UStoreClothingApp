package com.hasoftware.ustore.data.repository

import android.util.Log
import com.hasoftware.ustore.data.api.RetrofitClient
import com.hasoftware.ustore.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductRepository {
    
    private val apiService = RetrofitClient.apiService
    
    // Mock data for testing when backend is not available
    private val mockProducts = listOf(
        Product(
            id = 1,
            name = "iPhone 15 Pro",
            description = "iPhone 15 Pro với chip A17 Pro mạnh mẽ",
            price = 29990000.0,
            categoryId = 1,
            brand = "Apple",
            stockQuantity = 50,
            rating = 4.8,
            isActive = true,
            isFeatured = true,
            createdAt = "2024-01-15T10:00:00Z",
            updatedAt = "2024-01-15T10:00:00Z"
        ),
        Product(
            id = 2,
            name = "Samsung Galaxy S24 Ultra",
            description = "Samsung Galaxy S24 Ultra với camera 200MP",
            price = 27990000.0,
            categoryId = 1,
            brand = "Samsung",
            stockQuantity = 30,
            rating = 4.7,
            isActive = true,
            isFeatured = true,
            createdAt = "2024-01-16T10:00:00Z",
            updatedAt = "2024-01-16T10:00:00Z"
        ),
        Product(
            id = 3,
            name = "MacBook Pro M3",
            description = "MacBook Pro 14 inch với chip M3",
            price = 45990000.0,
            categoryId = 2,
            brand = "Apple",
            stockQuantity = 20,
            rating = 4.9,
            isActive = true,
            isFeatured = false,
            createdAt = "2024-01-17T10:00:00Z",
            updatedAt = "2024-01-17T10:00:00Z"
        ),
        Product(
            id = 4,
            name = "Dell XPS 13",
            description = "Dell XPS 13 với Intel Core i7",
            price = 32990000.0,
            categoryId = 2,
            brand = "Dell",
            stockQuantity = 25,
            rating = 4.6,
            isActive = true,
            isFeatured = false,
            createdAt = "2024-01-18T10:00:00Z",
            updatedAt = "2024-01-18T10:00:00Z"
        ),
        Product(
            id = 5,
            name = "AirPods Pro 2",
            description = "AirPods Pro thế hệ 2 với chống ồn chủ động",
            price = 5990000.0,
            categoryId = 3,
            brand = "Apple",
            stockQuantity = 100,
            rating = 4.8,
            isActive = true,
            isFeatured = true,
            createdAt = "2024-01-19T10:00:00Z",
            updatedAt = "2024-01-19T10:00:00Z"
        ),
        Product(
            id = 6,
            name = "Sony WH-1000XM5",
            description = "Sony WH-1000XM5 headphone chống ồn",
            price = 8990000.0,
            categoryId = 3,
            brand = "Sony",
            stockQuantity = 40,
            rating = 4.7,
            isActive = true,
            isFeatured = true,
            createdAt = "2024-01-20T10:00:00Z",
            updatedAt = "2024-01-20T10:00:00Z"
        )
    )
    
    suspend fun getProducts(page: Int = 0, size: Int = 20): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts(page, size)
                if (response.isSuccessful) {
                    Result.success(response.body()?.content ?: emptyList())
                } else {
                    Result.failure(Exception("Failed to fetch products: ${response.code()}"))
                }
            } catch (e: Exception) {
                Log.w("ProductRepository", "API không khả dụng, sử dụng mock data: ${e.message}")
                // Return mock data when API is not available
                val startIndex = page * size
                val endIndex = minOf(startIndex + size, mockProducts.size)
                val pagedProducts = if (startIndex < mockProducts.size) {
                    mockProducts.subList(startIndex, endIndex)
                } else {
                    emptyList()
                }
                Result.success(pagedProducts)
            }
        }
    }
    
    suspend fun getProductById(id: Long): Result<Product> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProductById(id)
                if (response.isSuccessful) {
                    Result.success(response.body() ?: throw Exception("Product not found"))
                } else {
                    Result.failure(Exception("Failed to fetch product: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    suspend fun searchProducts(keyword: String, page: Int = 0, size: Int = 20): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.searchProducts(keyword, page, size)
                if (response.isSuccessful) {
                    Result.success(response.body()?.content ?: emptyList())
                } else {
                    Result.failure(Exception("Failed to search products: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    suspend fun getProductsByCategory(categoryId: Long, page: Int = 0, size: Int = 20): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProductsByCategory(categoryId, page, size)
                if (response.isSuccessful) {
                    Result.success(response.body()?.content ?: emptyList())
                } else {
                    Result.failure(Exception("Failed to fetch products by category: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    suspend fun getFeaturedProducts(page: Int = 0, size: Int = 20): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getFeaturedProducts(page, size)
                if (response.isSuccessful) {
                    Result.success(response.body()?.content ?: emptyList())
                } else {
                    Result.failure(Exception("Failed to fetch featured products: ${response.code()}"))
                }
            } catch (e: Exception) {
                Log.w("ProductRepository", "API không khả dụng, sử dụng mock data cho featured products: ${e.message}")
                // Return mock featured products when API is not available
                val featuredProducts = mockProducts.filter { it.isFeatured }
                val startIndex = page * size
                val endIndex = minOf(startIndex + size, featuredProducts.size)
                val pagedProducts = if (startIndex < featuredProducts.size) {
                    featuredProducts.subList(startIndex, endIndex)
                } else {
                    emptyList()
                }
                Result.success(pagedProducts)
            }
        }
    }
    
    suspend fun getNewProducts(page: Int = 0, size: Int = 20): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getNewProducts(page, size)
                if (response.isSuccessful) {
                    Result.success(response.body()?.content ?: emptyList())
                } else {
                    Result.failure(Exception("Failed to fetch new products: ${response.code()}"))
                }
            } catch (e: Exception) {
                Log.w("ProductRepository", "API không khả dụng, sử dụng mock data cho new products: ${e.message}")
                // Return mock new products when API is not available
                val newProducts = mockProducts.sortedByDescending { it.createdAt }
                val startIndex = page * size
                val endIndex = minOf(startIndex + size, newProducts.size)
                val pagedProducts = if (startIndex < newProducts.size) {
                    newProducts.subList(startIndex, endIndex)
                } else {
                    emptyList()
                }
                Result.success(pagedProducts)
            }
        }
    }
}
