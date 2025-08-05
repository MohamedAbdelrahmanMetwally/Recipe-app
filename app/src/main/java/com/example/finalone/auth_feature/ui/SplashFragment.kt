package com.example.finalone.auth_feature.ui
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.finalone.R
import com.example.finalone.auth_feature.viewmodel.AuthViewModel
import com.example.finalone.databinding.FragmentSplashBinding
import com.example.finalone.recipe_feature.ui.RecipeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels()
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isFirstTime.observe(viewLifecycleOwner) { isFirstTime ->
            if (isFirstTime) {
                viewModel.setNotFirstTime()
                viewLifecycleOwner.lifecycleScope.launch {
                    delay(3000)
                    findNavController().navigate(R.id.action_navigation_splash_to_navigation_register)
                }
            }
            else if(!viewModel.isLoggedIn()){
                findNavController().navigate(R.id.action_navigation_splash_to_navigation_register)
            }
            else{
                startActivity(Intent(requireContext(), RecipeActivity::class.java))
            }
        }
    }
}