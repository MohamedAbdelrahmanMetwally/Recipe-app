package com.example.finalone.auth_feature.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.finalone.R
import com.example.finalone.auth_feature.viewmodel.AuthViewModel
import com.example.finalone.databinding.FragmentLoginBinding
import com.example.finalone.recipe_feature.ui.RecipeActivity

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: AuthViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLoginFinal.setOnClickListener {
            if (binding.etEmail.text.toString().isNullOrEmpty() || binding.etPassword.text.toString().isNullOrEmpty()){
                Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT).show()
            }
            else{
                if(viewModel.canLogin(binding.etEmail.text.toString(),binding.etPassword.text.toString())){
                    Toast.makeText(requireContext(), "login successfully", Toast.LENGTH_SHORT).show()
                    viewModel.login()
                    startActivity(Intent(requireContext(), RecipeActivity::class.java))
                }
            }
        }
    }
}