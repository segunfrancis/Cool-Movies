package com.project.segunfrancis.coolmovies.repository

import androidx.paging.PagingData
import com.project.segunfrancis.coolmovies.data.model.GenreResponse
import com.project.segunfrancis.coolmovies.data.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface MovieRepository {

    fun getTopRatedMovies(apiKey: String): Flow<PagingData<Result>>

    fun addFavorite(result: Result): Flow<Unit>

    fun removeFavorite(id: Int): Flow<Unit>

    fun getAllFavorites(): Flow<List<Result>>

    fun getGenresRemote(apiKey: String): Flow<GenreResponse>
}