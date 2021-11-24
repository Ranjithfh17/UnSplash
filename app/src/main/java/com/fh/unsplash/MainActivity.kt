package com.fh.unsplash

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navDeepLink
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.fh.unsplash.databinding.ActivityMainBinding
import com.google.android.material.internal.ViewUtils.requestApplyInsetsWhenAttached
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController=navHostFragment.navController



    }


}