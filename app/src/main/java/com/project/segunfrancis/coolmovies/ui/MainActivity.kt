package com.project.segunfrancis.coolmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.segunfrancis.coolmovies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}