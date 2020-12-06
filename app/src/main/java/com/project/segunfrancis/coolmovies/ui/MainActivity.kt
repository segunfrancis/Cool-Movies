package com.project.segunfrancis.coolmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.project.segunfrancis.coolmovies.databinding.ActivityMainBinding
import com.project.segunfrancis.coolmovies.util.State
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainViewModel by viewModels()
        viewModel.genreIDs.observe(this) { state ->
            when(state) {
                is State.Error -> {
                    Timber.d(state.error.localizedMessage)
                }
            }
        }
    }
}