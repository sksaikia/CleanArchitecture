package com.example.githubapp.feature_search.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialFragment()
    }

    private fun initialFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, UserFragment())
            .commit()
    }
}