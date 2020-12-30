package com.project.segunfrancis.coolmovies.ui.popular

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.project.segunfrancis.coolmovies.domain.usecase.GetPopularMoviesUseCase
import com.project.segunfrancis.coolmovies.ui.mapper.ResultMapper
import com.project.segunfrancis.coolmovies.ui.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class PopularViewModel @ViewModelInject constructor(
    private val apiKey: String,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val resultMapper: ResultMapper
) : ViewModel() {

    private var currentMovieResult: Flow<PagingData<Result>>? = null

    fun getPopularMovies(): Flow<PagingData<Result>> {
        val lastResult = currentMovieResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult = getPopularMoviesUseCase.execute(apiKey).cachedIn(viewModelScope).map {
            it.map { resultDomain ->
                resultMapper.mapDataToDomainLayer(resultDomain)
            }
        }
        currentMovieResult = newResult
        Timber.d(newResult.toString())
        return newResult
    }
}