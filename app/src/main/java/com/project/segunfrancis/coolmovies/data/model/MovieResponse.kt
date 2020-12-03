package com.project.segunfrancis.coolmovies.data.model

import com.project.segunfrancis.coolmovies.data.model.Result

data class MovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)