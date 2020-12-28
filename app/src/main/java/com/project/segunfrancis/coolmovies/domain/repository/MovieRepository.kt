package com.project.segunfrancis.coolmovies.domain.repository

import androidx.paging.PagingData
import com.project.segunfrancis.coolmovies.domain.model.GenreDomain
import com.project.segunfrancis.coolmovies.domain.model.GenreResponseDomain
import com.project.segunfrancis.coolmovies.domain.model.ResultDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface MovieRepository {

    fun getTopRatedMovies(apiKey: String): Flow<PagingData<ResultDomain>>

    fun getPopularMovies(apiKey: String): Flow<PagingData<ResultDomain>>

    fun getNowPlayingMovies(apiKey: String): Flow<PagingData<ResultDomain>>

    fun addFavorite(result: ResultDomain): Flow<Unit>

    fun removeFavorite(id: Int): Flow<Unit>

    fun getAllFavorites(): Flow<List<ResultDomain>>

    fun getGenresRemote(apiKey: String): Flow<GenreResponseDomain>

    fun insertGenreIDs(genre: GenreDomain): Flow<Unit>

    fun getGenreLocalIDs(): Flow<List<GenreDomain>>
}