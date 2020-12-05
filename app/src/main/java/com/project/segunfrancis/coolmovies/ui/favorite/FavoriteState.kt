package com.project.segunfrancis.coolmovies.ui.favorite

import com.google.android.material.snackbar.Snackbar

/**
 * Created by SegunFrancis
 *
 * This class manages the [Snackbar] message and the color of the [Snackbar] that is
 * displayed when a movie is added to or removed from favorites list
 */

data class FavoriteState(val message: String, val warning: Boolean)