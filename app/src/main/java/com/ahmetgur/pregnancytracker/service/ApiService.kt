package com.ahmetgur.pregnancytracker.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://raw.githubusercontent.com/ahmetgurr/JSONPregnancyTrackerCategories/main/categories.json
//https://raw.githubusercontent.com/
//ahmetgurr/JSONPregnancyTrackerCategories/main/categories.json

private val retrofit = Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val discoverService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("ahmetgurr/JSONPregnancyTrackerCategories/main/categories.json")
    suspend fun getCategories(): CategoriesResponse

}