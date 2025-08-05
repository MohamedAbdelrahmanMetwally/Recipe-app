package com.example.finalone.recipe_feature.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalone.R
import com.example.finalone.databinding.FragmentFavoriteBinding
import com.example.finalone.recipe_feature.util.MealsAdapter
import com.example.finalone.recipe_feature.viewmodel.MealViewModel

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: MealViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        binding.rvFavourite.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavourite.adapter = MealsAdapter(emptyList(),true)
        binding.progressBar.visibility = View.VISIBLE
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[MealViewModel::class.java]
        viewModel.allMeals.observe(viewLifecycleOwner) {
                meals ->
            binding.progressBar.visibility = View.GONE
            binding.rvFavourite.adapter = MealsAdapter(meals,true)
            binding.rvFavourite.adapter?.notifyDataSetChanged()
        }
    }
}