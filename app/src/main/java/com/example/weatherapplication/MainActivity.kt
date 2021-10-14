package com.example.weatherapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.View
import com.example.weatherapplication.databinding.ActivityMainBinding
import com.example.weatherapplication.utils.getApplicationTheme
import com.example.weatherapplication.utils.getMainViewColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getApplicationTheme())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sets background color of the application depending on the time of the day
        binding.mainView.setBackgroundColor(resources.getColor(getMainViewColor()))



        setSupportActionBar(binding.toolbar)

        // Sets up the navController
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)



        // Removes the toolbar from the fragment based on destination
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val id = destination.id
            when (id) {
                R.id.CityDetailFragment -> binding.toolbar.visibility = View.GONE
                else -> binding.toolbar.visibility = View.GONE
            }
        }


    }

    // Handles navigating by pressing the back button
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}