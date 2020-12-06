package com.project.segunfrancis.coolmovies.ui.all.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.project.segunfrancis.coolmovies.R
import com.project.segunfrancis.coolmovies.ui.model.Result
import com.project.segunfrancis.coolmovies.databinding.ItemMovieListBinding

/**
 * Created by SegunFrancis
 */

class MovieAdapter(private val onClick: (result: Result?) -> Unit) :
    PagingDataAdapter<Result, MovieViewHolder>(MovieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return MovieViewHolder(ItemMovieListBinding.bind(view), onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object MovieDiffUtil : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}