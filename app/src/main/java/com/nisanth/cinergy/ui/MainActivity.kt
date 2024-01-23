package com.nisanth.cinergy.ui

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.nisanth.cinergy.R
import com.nisanth.cinergy.databinding.ActivityMainBinding
import com.nisanth.cinergy.di.blockUI
import com.nisanth.cinergy.di.hide
import com.nisanth.cinergy.di.show
import com.nisanth.cinergy.di.unBlockUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            tvBack.setOnClickListener {
                onBackPressed()
            }
        }
        setContentView(binding?.root)
    }

    override fun onBackPressed() {
        findNavController(R.id.navHostContainer).apply {
            if (currentDestination?.id != null) {
                when (currentDestination?.id) {
                    R.id.landingFragment -> finish()
                    else -> navigateUp()
                }
            } else super.onBackPressed()
        }
    }

    fun showProgress() {
        binding?.progressBar?.blockUI(this)

    }

    fun hideProgress() {
        binding?.progressBar?.unBlockUI(this)
    }

}