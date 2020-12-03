package com.project.segunfrancis.coolmovies.ui.all.adapter

import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.coolmovies.data.remote.model.Result
import com.project.segunfrancis.coolmovies.databinding.ItemMovieListBinding
import com.project.segunfrancis.coolmovies.util.AppConstants
import com.project.segunfrancis.coolmovies.util.loadImage

/**
 * Created by SegunFrancis
 */

class MovieViewHolder(
    private val binding: ItemMovieListBinding,
    private val onClick: (result: Result?) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result?) = with(binding) {
        movieTitle.text = result?.title
        movieThumbnail.loadImage(
            AppConstants.IMAGE_BASE_URL.plus(AppConstants.POSTER_SIZE).plus(result?.poster_path)
        )
        root.setOnClickListener { onClick(result) }
    }
}