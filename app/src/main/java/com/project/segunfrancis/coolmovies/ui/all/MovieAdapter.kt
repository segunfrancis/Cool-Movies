package com.project.segunfrancis.coolmovies.ui.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.coolmovies.R
import com.project.segunfrancis.coolmovies.data.remote.model.Result
import com.project.segunfrancis.coolmovies.databinding.ItemMovieListBinding

/**
 * Created by SegunFrancis
 */

class MovieAdapter : PagingDataAdapter<Result, MovieAdapter.MovieViewHolder>(MovieDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return MovieViewHolder(ItemMovieListBinding.bind(view))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind()
    }


    class MovieViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

        }
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