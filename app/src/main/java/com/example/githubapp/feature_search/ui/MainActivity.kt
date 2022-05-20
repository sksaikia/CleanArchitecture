package com.example.githubapp.feature_search.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialFragment()
    }

    private fun initialFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, UserFragment())
            .commit()
    }
}