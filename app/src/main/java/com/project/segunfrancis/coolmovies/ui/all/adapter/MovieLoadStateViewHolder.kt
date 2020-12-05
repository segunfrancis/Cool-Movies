package com.project.segunfrancis.coolmovies.ui.all.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.coolmovies.R
import com.project.segunfrancis.coolmovies.databinding.ItemLoadStateBinding
import java.net.SocketTimeoutException
import java.net.UnknownHostException

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
            errorMsg.text = when(loadState.error) {
                is UnknownHostException -> root.context.resources.getString(R.string.text_error_unknown_host_exception)
                is SocketTimeoutException -> root.context.resources.getString(R.string.text_error_socket_timeout_exception)
                else -> root.context.resources.getString(R.string.text_error_general)
            }
        }
        progressBar.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState !is LoadState.Loading
        errorMsg.isVisible = loadState !is LoadState.Loading
    }
}