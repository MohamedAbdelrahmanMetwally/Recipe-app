package com.example.finalone.recipe_feature.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalone.R
import com.example.finalone.core.database.Meal

class MealsAdapter( var meals: List<Meal>,var isFavourite: Boolean)
    : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMeal: TextView = itemView.findViewById(R.id.tvMeal)
        val ivThumbMeal: ImageView = itemView.findViewById(R.id.ivThumbMeal)
        val cvMeal: CardView = itemView.findViewById(R.id.cvMeal)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_card, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = meals.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = meals[position]
        holder.tvMeal.text = meal.strMeal
        Glide.with(holder.itemView.context)
            .load(meal.strMealThumb)
            .into(holder.ivThumbMeal)
        holder.cvMeal.setOnClickListener {
            val bundle = Bundle().apply {
                putString("mealId", meal.idMeal)
                putBoolean("isFavourite", isFavourite)
            }
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.to_navigation_recipe_detail, bundle)
        }
    }
}