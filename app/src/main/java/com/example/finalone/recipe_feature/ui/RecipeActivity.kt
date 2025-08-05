package com.example.finalone.recipe_feature.ui
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.finalone.R
import com.example.finalone.auth_feature.ui.AuthActivity
import com.example.finalone.core.database.SharedPref
import com.example.finalone.databinding.ActivityRecipeBinding
class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_recipe)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_search,
                R.id.navigation_favourite,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                SharedPref.logout(this)
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
                true
            }
            R.id.navigation_about_creator-> {
                findNavController(R.id.nav_host_fragment_activity_recipe)
                    .navigate(R.id.to_about_creator_fragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}