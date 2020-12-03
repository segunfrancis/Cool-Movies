package com.project.segunfrancis.coolmovies.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.project.segunfrancis.coolmovies.R
import com.project.segunfrancis.coolmovies.data.model.Result
import com.project.segunfrancis.coolmovies.databinding.ItemMovieListBinding

/**
 * Created by SegunFrancis
 */

class FavoriteMovieAdapter(private val onClick: (result: Result) -> Unit) :
    ListAdapter<Result, FavoriteMovieViewHolder>(FavoriteDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return FavoriteMovieViewHolder(ItemMovieListBinding.bind(view), onClick)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object FavoriteDiffUtil : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}