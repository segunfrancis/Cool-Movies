package com.project.segunfrancis.coolmovies.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.segunfrancis.coolmovies.data.local.db.MovieDatabase
import com.project.segunfrancis.coolmovies.data.model.GenreResponse
import com.project.segunfrancis.coolmovies.data.remote.api.MovieApi
import com.project.segunfrancis.coolmovies.data.model.Result
import com.project.segunfrancis.coolmovies.data.remote.source.MoviePagingSource
import com.project.segunfrancis.coolmovies.util.AppConstants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val db: MovieDatabase
) : MovieRepository {
    override fun getTopRatedMovies(apiKey: String): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                MoviePagingSource(api, apiKey)
            }).flow
    }

    override fun addFavorite(result: Result): Flow<Unit> {
        return flow {
            emit(db.movieDao().addFavorite(result))
        }
    }

    override fun removeFavorite(id: Int): Flow<Unit> {
        return flow {
            emit(db.movieDao().removeFavorite(id))
        }
    }

    override fun getAllFavorites(): Flow<List<Result>> {
        return db.movieDao().getAllFavorites()
    }

    override fun getGenresRemote(apiKey: String): Flow<GenreResponse> {
        return flow {
            emit(api.getGenres(apiKey))
        }
    }
}