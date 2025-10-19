package com.hasoftware.ustore.data.repository

import com.hasoftware.ustore.data.api.RetrofitClient
import com.hasoftware.ustore.data.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository {
    
    private val apiService = RetrofitClient.apiService
    
    suspend fun getCategories(): Result<List<Category>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCategories()
                if (response.isSuccessful) {
                    Result.success(response.body() ?: emptyList())
                } else {
                    Result.failure(Exception("Failed to fetch categories: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    suspend fun getActiveCategories(): Result<List<Category>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getActiveCategories()
                if (response.isSuccessful) {
                    Result.success(response.body() ?: emptyList())
                } else {
                    Result.failure(Exception("Failed to fetch active categories: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    suspend fun getCategoryById(id: Long): Result<Category> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCategoryById(id)
                if (response.isSuccessful) {
                    Result.success(response.body() ?: throw Exception("Category not found"))
                } else {
                    Result.failure(Exception("Failed to fetch category: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
