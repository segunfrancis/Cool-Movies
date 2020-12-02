package com.project.segunfrancis.coolmovies.util

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.project.segunfrancis.coolmovies.R

/**
 * Created by SegunFrancis
 */

fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(loadingIndicator(this.context))
        .error(R.drawable.ic_error)
        .into(this)
}

fun loadingIndicator(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 6F
        centerRadius = 26F
        setColorSchemeColors(
            Color.rgb(38, 205, 152),
            Color.rgb(32, 77, 211),
            Color.rgb(228, 40, 56)
        )
        start()
    }
}