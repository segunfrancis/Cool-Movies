package com.project.segunfrancis.coolmovies.data.mapper

import com.project.segunfrancis.coolmovies.data.remote.model.GenreResponse
import com.project.segunfrancis.coolmovies.domain.model.GenreDomain
import com.project.segunfrancis.coolmovies.domain.model.GenreResponseDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */
class GenreMapper @Inject constructor() : Mapper<GenreResponse, GenreResponseDomain> {
    override fun mapDataToDomainLayer(data: GenreResponse): GenreResponseDomain {
        return with(data) {
            GenreResponseDomain(genres.map { genre ->
                with(genre) {
                    GenreDomain(id, name)
                }
            })
        }
    }
}