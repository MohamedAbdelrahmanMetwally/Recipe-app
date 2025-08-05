package com.example.finalone.recipe_feature.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalone.recipe_feature.viewmodel.MealViewModel

class MealViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            return MealViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
