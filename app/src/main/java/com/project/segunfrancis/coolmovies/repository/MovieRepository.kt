package com.project.segunfrancis.coolmovies.repository

import androidx.paging.PagingData
import com.project.segunfrancis.coolmovies.data.remote.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface MovieRepository {

    fun getTopRatedMovies(apiKey: String): Flow<PagingData<Result>>
}