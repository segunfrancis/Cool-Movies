package com.project.segunfrancis.data.remote.api

import com.project.segunfrancis.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by SegunFrancis
 */

interface MovieApi {

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): MovieResponse
}