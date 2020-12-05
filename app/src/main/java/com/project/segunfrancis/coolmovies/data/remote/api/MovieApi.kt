package com.project.segunfrancis.coolmovies.data.remote.api

import com.project.segunfrancis.coolmovies.data.model.GenreResponse
import com.project.segunfrancis.coolmovies.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by SegunFrancis
 */

interface MovieApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): MovieResponse

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): GenreResponse
}