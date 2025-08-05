package com.example.finalone.auth_feature.ui
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.finalone.R
import com.example.finalone.auth_feature.viewmodel.AuthViewModel
import com.example.finalone.databinding.FragmentRegisterBinding
import com.example.finalone.recipe_feature.ui.RecipeActivity

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: AuthViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            if (binding.etEmailRegister.text.toString()
                    .isNullOrEmpty() || binding.etPasswordRegister.text.toString()
                    .isNullOrEmpty() || binding.etUsername.text.toString()
                    .isNullOrEmpty() || binding.etAge.text.toString()
                    .isNullOrEmpty() || binding.etFirstName.text.toString()
                    .isNullOrEmpty() || binding.etLastName.text.toString()
                    .isNullOrEmpty() || binding.etMiddleName.text.toString().isNullOrEmpty()
            ) {
                Toast.makeText(requireContext(), "please fill all fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.register(
                    binding.etEmailRegister.text.toString(),
                    binding.etPasswordRegister.text.toString()
                )
                viewModel.login()
                startActivity(Intent(requireContext(), RecipeActivity::class.java))
            }
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_register_to_navigation_login)
        }
    }
}