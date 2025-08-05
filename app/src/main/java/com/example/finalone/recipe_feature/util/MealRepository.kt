package com.example.finalone.recipe_feature.util
import androidx.lifecycle.LiveData
import com.example.finalone.core.database.Meal
import com.example.finalone.core.database.MealDao
class MealRepository(private val mealDao: MealDao) {
    val allMeals: LiveData<List<Meal>> = mealDao.getAllMeals()
    suspend fun insertMeal(meal: Meal) {
        mealDao.insertMeal(meal)
    }
    suspend fun deleteMeal(meal: Meal) {
        mealDao.deleteMeal(meal)
    }
    suspend fun clearAllMeals() {
        mealDao.clearAllMeals()
    }
    fun searchMeals(query: String): LiveData<List<Meal>> {
        return mealDao.searchMeals(query)
    }
    fun getMealById(id: String): Meal {
        return mealDao.getMealById(id)
    }
}