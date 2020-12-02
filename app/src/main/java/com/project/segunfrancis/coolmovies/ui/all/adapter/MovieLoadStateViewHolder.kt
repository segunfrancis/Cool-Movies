package com.project.segunfrancis.coolmovies.ui.all.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.coolmovies.databinding.ItemLoadStateBinding

/**
 * Created by SegunFrancis
 */

class MovieLoadStateViewHolder(
    private val binding: ItemLoadStateBinding,
    private val retry: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) = with(binding) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }
        progressBar.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState !is LoadState.Loading
        errorMsg.isVisible = loadState !is LoadState.Loading
    }
}