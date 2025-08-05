package com.example.finalone.recipe_feature.ui
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.finalone.R
import com.example.finalone.core.database.Meal
import com.example.finalone.core.network.RetrofitBuilder
import com.example.finalone.databinding.FragmentRecipeDetailBinding
import com.example.finalone.recipe_feature.util.MealViewModelFactory
import com.example.finalone.recipe_feature.viewmodel.MealViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeDetailFragment : Fragment() {
    private lateinit var meal: Meal
    private lateinit var binding: FragmentRecipeDetailBinding
    private val viewModel: MealViewModel by viewModels {
        MealViewModelFactory(requireActivity().application)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        val mealId = arguments?.getString("mealId")?:""
        viewModel.getMealById(mealId).observe(viewLifecycleOwner) { meal ->
            if (meal != null) {
                this.meal = meal
                updateUI(meal)
            }
        }
        binding.btnWatchVideo.setOnClickListener {
            meal.strYoutube?.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(intent)
            }
        }
        binding.btnSetAsFavourite.setOnClickListener {
            if(binding.btnSetAsFavourite.text=="Set as favourite"){
                viewModel.insertMeal(meal)
                binding.btnSetAsFavourite.text="Remove from favourites"
            }else{
                viewModel.deleteMeal(meal)
                binding.btnSetAsFavourite.text="Set as favourite"
            }
        }
    }
    private fun updateUI(meal: Meal) {
        binding.tvMealName.text = meal.strMeal
        binding.tvCategoryDetail.text = meal.strCategory
        binding.tvAreaDetail.text = meal.strArea
        binding.tvInstructions.text = meal.strInstructions

        Glide.with(this)
            .load(meal.strMealThumb)
            .into(binding.ivMealThumb)

        val ingredientsText = listOf(
            meal.strIngredient1 to meal.strMeasure1,
            meal.strIngredient2 to meal.strMeasure2,
            meal.strIngredient3 to meal.strMeasure3,
            meal.strIngredient4 to meal.strMeasure4,
            meal.strIngredient5 to meal.strMeasure5,
            meal.strIngredient6 to meal.strMeasure6,
            meal.strIngredient7 to meal.strMeasure7,
            meal.strIngredient8 to meal.strMeasure8,
            meal.strIngredient9 to meal.strMeasure9,
            meal.strIngredient10 to meal.strMeasure10
        ).filter { !it.first.isNullOrBlank() }
            .joinToString("\n") { (ingredient, measure) -> "$ingredient - ${measure ?: ""}" }

        binding.tvIngredients.text = ingredientsText
    }
}