package com.hasoftware.ustore.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    val currentUser: FirebaseUser? get() = firebaseAuth.currentUser
    val isUserLoggedIn: Boolean get() = currentUser != null

    suspend fun signUp(email: String, password: String, fullName: String): Result<FirebaseUser> {
        return try {
            Log.d("AuthRepository", "Bắt đầu đăng ký với email: $email")
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            
            if (user != null) {
                Log.d("AuthRepository", "Tạo tài khoản Firebase thành công: ${user.uid}")
                
                // Lưu thông tin user vào Firestore (không bắt buộc)
                try {
                    val userData = hashMapOf(
                        "uid" to user.uid,
                        "email" to email,
                        "fullName" to fullName,
                        "createdAt" to System.currentTimeMillis()
                    )
                    
                    firestore.collection("users")
                        .document(user.uid)
                        .set(userData)
                        .await()
                    
                    Log.d("AuthRepository", "Lưu dữ liệu Firestore thành công")
                } catch (firestoreError: Exception) {
                    // Log lỗi nhưng không làm fail toàn bộ quá trình đăng ký
                    Log.w("AuthRepository", "Lỗi khi lưu dữ liệu Firestore: ${firestoreError.message}")
                }
                
                Log.d("AuthRepository", "Đăng ký hoàn tất thành công")
                Result.success(user)
            } else {
                Log.e("AuthRepository", "Không thể tạo tài khoản - user null")
                Result.failure(Exception("Không thể tạo tài khoản"))
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Lỗi đăng ký: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("Đăng nhập thất bại"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signOut(): Result<Unit> {
        return try {
            firebaseAuth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun resetPassword(email: String): Result<Unit> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

