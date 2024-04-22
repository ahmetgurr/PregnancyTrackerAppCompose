package com.ahmetgur.pregnancytracker.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class UserRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore)
{
    suspend fun signUp(email: String, password: String, firstName: String, lastName: String
    ): Result<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(firstName, lastName, email)
            saveUserToFirestore(user)
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error(e)
        }

    private suspend fun saveUserToFirestore(user: User) {
        firestore.collection("users").document(user.email).set(user).await()
    }

    suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun logout() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            Log.e("UserRepository", "Error logging out: ${e.message}", e)
        }
    }

    suspend fun resetPassword(email: String): Result<Boolean> =
        try {
            auth.sendPasswordResetEmail(email).await()
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun deleteAccount(): Result<Boolean> =
        try {
            auth.currentUser?.delete()?.await()
            Result.Success(true)
        }catch (e: Exception) {
            Result.Error(e)
        }
}
