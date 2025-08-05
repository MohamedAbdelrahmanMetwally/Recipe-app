package com.example.finalone.recipe_feature.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.finalone.core.database.Meal
import com.example.finalone.core.database.MealDatabase
import com.example.finalone.core.network.ApiService
import com.example.finalone.core.network.RetrofitBuilder
import com.example.finalone.recipe_feature.util.MealRepository
import kotlinx.coroutines.launch

class MealViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MealRepository
    val allMeals: LiveData<List<Meal>>
    init {
        val mealDao = MealDatabase.getInstance(application).mealDao()
        repository = MealRepository(mealDao)
        allMeals = repository.allMeals
    }
    fun insertMeal(meal: Meal) = viewModelScope.launch {
        repository.insertMeal(meal)
    }
    fun deleteMeal(meal: Meal) = viewModelScope.launch {
        repository.deleteMeal(meal)
    }
    fun clearAllMeals() = viewModelScope.launch {
        repository.clearAllMeals()
    }
    fun searchMeals(query: String): LiveData<List<Meal>> {
        return repository.searchMeals(query)
    }
    fun getMealById(id: String) = liveData {
        val mealFromDb = repository.getMealById(id)
        if (mealFromDb != null) {
            emit(mealFromDb)
        } else {
            val mealFromApi = RetrofitBuilder().getInstanse().getMealDetails(id).meals?.firstOrNull()
            if (mealFromApi != null) emit(mealFromApi)
        }
    }

}