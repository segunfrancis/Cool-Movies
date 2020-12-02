package com.project.segunfrancis.coolmovies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.segunfrancis.coolmovies.data.remote.api.MovieApi
import com.project.segunfrancis.coolmovies.data.remote.model.Result
import com.project.segunfrancis.coolmovies.data.remote.source.MoviePagingSource
import com.project.segunfrancis.coolmovies.util.AppConstants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi): MovieRepository {
    override fun getTopRatedMovies(apiKey: String): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                MoviePagingSource(api, apiKey)
            }).flow
    }
}