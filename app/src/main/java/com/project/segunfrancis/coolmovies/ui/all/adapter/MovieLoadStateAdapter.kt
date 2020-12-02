package com.project.segunfrancis.coolmovies.ui.all.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.project.segunfrancis.coolmovies.R
import com.project.segunfrancis.coolmovies.databinding.ItemLoadStateBinding

/**
 * Created by SegunFrancis
 */

class MovieLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MovieLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: MovieLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieLoadStateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false)
        return MovieLoadStateViewHolder(ItemLoadStateBinding.bind(view), retry)
    }
}