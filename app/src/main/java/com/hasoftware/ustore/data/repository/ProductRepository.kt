package com.hasoftware.ustore.data.repository

import com.hasoftware.ustore.data.api.RetrofitClient
import com.hasoftware.ustore.data.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductRepository {
    
    private val apiService = RetrofitClient.apiService
    
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
                Result.failure(e)
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
                Result.failure(e)
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
                Result.failure(e)
            }
        }
    }
}
