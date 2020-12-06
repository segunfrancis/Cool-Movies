package com.project.segunfrancis.coolmovies.data.mapper

import com.project.segunfrancis.coolmovies.data.remote.model.Result
import com.project.segunfrancis.coolmovies.domain.model.ResultDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class ResultMapper @Inject constructor() : Mapper<Result, ResultDomain> {
    override fun mapDataToDomainLayer(data: Result): ResultDomain {
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
}