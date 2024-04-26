package com.ahmetgur.pregnancytracker.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class BabyRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
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
