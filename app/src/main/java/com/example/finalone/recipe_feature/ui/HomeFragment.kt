package com.example.finalone.recipe_feature.ui
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalone.R
import com.example.finalone.core.database.Meal
import com.example.finalone.core.network.RetrofitBuilder
import com.example.finalone.databinding.FragmentHomeBinding
import com.example.finalone.recipe_feature.util.MealsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class HomeFragment : Fragment() {
    private lateinit var adapter: MealsAdapter
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        var meals= mutableListOf<Meal>()
        adapter = MealsAdapter(meals,false)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        val retrofitBuilder = RetrofitBuilder().getInstanse()
        binding.progressBar.visibility = View.VISIBLE
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = retrofitBuilder.getMeals()
                withContext(Dispatchers.Main) {
                    binding.progressBar.visibility = View.GONE
                    adapter.meals= response.meals!!
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.progressBar.visibility = View.GONE
                }
                e.printStackTrace()
            }
        }
    }
}