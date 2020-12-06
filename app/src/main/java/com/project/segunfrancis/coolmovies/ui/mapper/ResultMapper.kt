package com.project.segunfrancis.coolmovies.ui.mapper

import com.project.segunfrancis.coolmovies.domain.model.ResultDomain
import com.project.segunfrancis.coolmovies.ui.model.Result
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class ResultMapper @Inject constructor() : Mapper<Result, ResultDomain> {
    override fun mapDomainToDataLayer(data: Result): ResultDomain {
        return with(data) {
            ResultDomain(
                adult,
                backdrop_path,
                genre_ids,
                id,
                original_language,
                original_title,
                overview,
                popularity,
                poster_path,
                release_date,
                title,
                video,
                vote_average,
                vote_count
            )
        }
    }

    override fun mapDataToDomainLayer(data: ResultDomain): Result {
        return with(data) {
            Result(
                adult,
                backdrop_path,
                genre_ids,
                id,
                original_language,
                original_title,
                overview,
                popularity,
                poster_path,
                release_date,
                title,
                video,
                vote_average,
                vote_count
            )
        }
    }
}