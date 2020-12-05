package com.project.segunfrancis.coolmovies.data.model

data class MovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)