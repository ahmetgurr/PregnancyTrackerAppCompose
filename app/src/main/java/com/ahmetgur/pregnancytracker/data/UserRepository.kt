package com.ahmetgur.pregnancytracker.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class UserRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore)
{
    suspend fun signUp(email: String, password: String, username: String
    ): Result<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(username, email)
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

    suspend fun getUserData(): Result<User> =
        try {
            val documentSnapshot = firestore.collection("users")
                .document(auth.currentUser?.email ?: "")
                .get()
                .await()
            val user = documentSnapshot.toObject(User::class.java)
            if (user != null) {
                Result.Success(user)
            } else {
                Result.Error(Exception("User not found"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun updateUserData(user: User): Result<User> =
        try {
            firestore.collection("users")
                .document(auth.currentUser?.email ?: "")
                .set(user)
                .await()
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun getBabyData(): Result<Baby> =
        try {
            val documentSnapshot = firestore.collection("users")
                .document(auth.currentUser?.email ?: "")
                .collection("babys")
                .document("baby")
                .get()
                .await()
            val baby = documentSnapshot.toObject(Baby::class.java)
            if (baby != null) {
                Result.Success(baby)
            } else {
                Result.Error(Exception("Baby not found"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }


    suspend fun updateBabyData(baby: Baby): Result<Baby> =
        try {
            val documentReference = firestore.collection("users")
                .document(auth.currentUser?.email ?: "")
                .collection("babys")
                .document("baby")
            baby.id = documentReference.id
            documentReference.set(baby).await()
            Result.Success(baby)
        } catch (e: Exception) {
            Result.Error(e)
        }



}
