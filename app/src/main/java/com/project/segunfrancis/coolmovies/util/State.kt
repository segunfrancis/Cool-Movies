package com.project.segunfrancis.coolmovies.util

/**
 * Created by SegunFrancis
 */

sealed class State<out T> {
    data class Success<T>(val data: T? = null) : State<T>()
    class Error(val error: Throwable) : State<Nothing>()
    object Loading : State<Nothing>()
}