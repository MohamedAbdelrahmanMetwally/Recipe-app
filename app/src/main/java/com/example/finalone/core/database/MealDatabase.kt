package com.example.finalone.core.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(
    entities = [Meal::class],
    version = 1,
    exportSchema = false
)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null
        @Synchronized
        fun getInstance(context: Context): MealDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, MealDatabase::class.java, "meal_database").fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }
    }
}