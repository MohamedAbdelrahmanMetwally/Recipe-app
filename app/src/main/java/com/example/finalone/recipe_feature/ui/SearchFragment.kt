package com.example.finalone.recipe_feature.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalone.R
import com.example.finalone.core.database.Meal
import com.example.finalone.databinding.FragmentSearchBinding
import com.example.finalone.recipe_feature.util.MealsAdapter
import com.example.finalone.recipe_feature.viewmodel.MealViewModel
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    private lateinit var _binding: FragmentSearchBinding
    private val viewModel: MealViewModel by viewModels()
    private lateinit var mealsAdapter: MealsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return _binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        val list= mutableListOf<Meal>()
        mealsAdapter = MealsAdapter(list,true)
        _binding.rvSearchResults.layoutManager = LinearLayoutManager(requireContext())
        _binding.rvSearchResults.adapter = mealsAdapter
        _binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {  }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /* Not needed */ }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                _binding.progressBar.visibility = View.VISIBLE
                val query = s?.toString()?.trim() ?: ""
                lifecycleScope.launch {viewModel.searchMeals(query).observe(viewLifecycleOwner) { meals ->
                    _binding.progressBar.visibility = View.GONE
                    mealsAdapter.meals = meals
                    mealsAdapter.notifyDataSetChanged()
                }
                }
            }
        })
    }

}