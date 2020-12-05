package com.project.segunfrancis.coolmovies.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.coolmovies.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException

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

fun View.snack(message: String, warning: Boolean) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    if (warning)
        snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.custom_yellow))
    else
        snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.custom_green))
    snackbar.show()
}

fun Throwable.errorMessage(context: Context): String {
    return when(this) {
        is UnknownHostException -> context.resources.getString(R.string.text_error_unknown_host_exception)
        is SocketTimeoutException -> context.resources.getString(R.string.text_error_socket_timeout_exception)
        else -> context.resources.getString(R.string.text_error_general)
    }
}