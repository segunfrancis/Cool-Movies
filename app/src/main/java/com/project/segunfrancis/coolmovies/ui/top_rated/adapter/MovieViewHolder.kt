package com.project.segunfrancis.coolmovies.ui.top_rated.adapter

import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.coolmovies.ui.model.Result
import com.project.segunfrancis.coolmovies.databinding.ItemMovieListBinding
import com.project.segunfrancis.coolmovies.util.AppConstants.IMAGE_BASE_URL
import com.project.segunfrancis.coolmovies.util.AppConstants.POSTER_SIZE
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
            IMAGE_BASE_URL.plus(POSTER_SIZE).plus(result?.poster_path)
        )
        root.setOnClickListener { onClick(result) }
    }
}