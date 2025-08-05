package com.example.finalone.core.network

import com.example.finalone.core.database.Meal
import com.example.finalone.core.util.Meals
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("filter.php?c=Seafood")
    suspend fun getMeals(): Meals
    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") id: String): Meals
}