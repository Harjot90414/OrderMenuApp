package com.harjot.ordermenuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNav : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    lateinit var navController:NavController
    var arraylist =ArrayList<MenuModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav)
        bottomNav = findViewById(R.id.bottomNav)
        navController = findNavController(R.id.navController)

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.order -> navController.navigate(R.id.order)
                R.id.menu -> navController.navigate(R.id.menu)

            }
            return@setOnItemSelectedListener true
        }
    }
}
