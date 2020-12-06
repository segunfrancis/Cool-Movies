package com.project.segunfrancis.coolmovies.data.mapper

import com.project.segunfrancis.coolmovies.data.local.model.ResultLocal
import com.project.segunfrancis.coolmovies.domain.model.ResultDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class ResultLocalMapper @Inject constructor() : Mapper<ResultLocal, ResultDomain> {
    override fun mapDataToDomainLayer(data: ResultLocal): ResultDomain {
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

    override fun mapDomainToDataLayer(data: ResultDomain): ResultLocal {
        return with(data) {
            ResultLocal(
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