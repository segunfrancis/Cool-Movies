package com.project.segunfrancis.coolmovies.ui.all

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.segunfrancis.coolmovies.data.model.Result
import com.project.segunfrancis.coolmovies.usecase.GetTopRatedMoviesUseCase
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class AllMoviesViewModel @ViewModelInject constructor(
    private val apiKey: String,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) :
    ViewModel() {

    private var currentMovieResult: Flow<PagingData<Result>>? = null

    fun getTopRatedMovies(): Flow<PagingData<Result>> {
        val lastResult = currentMovieResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult = getTopRatedMoviesUseCase.execute(apiKey).cachedIn(viewModelScope)
        currentMovieResult = newResult
        Timber.d(newResult.toString())
        return newResult
    }
}