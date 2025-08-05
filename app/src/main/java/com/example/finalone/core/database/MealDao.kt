package com.example.finalone.core.database
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)
    @Delete
    suspend fun deleteMeal(meal: Meal)
    @Query("SELECT * FROM meal")
    fun getAllMeals(): LiveData<List<Meal>>
    @Query("DELETE FROM meal")
    suspend fun clearAllMeals()
    @Query("SELECT * FROM meal WHERE strMeal LIKE '%' || :query || '%'")
    fun searchMeals(query: String): LiveData<List<Meal>>
    @Query("SELECT * FROM meal WHERE idMeal = :id")
    fun getMealById(id: String): Meal
}
