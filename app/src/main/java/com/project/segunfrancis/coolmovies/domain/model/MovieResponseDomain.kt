package com.project.segunfrancis.coolmovies.domain.model

data class MovieResponseDomain(
    val page: Int,
    val results: List<ResultDomain>,
    val total_pages: Int,
    val total_results: Int
)