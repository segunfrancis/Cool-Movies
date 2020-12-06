package com.project.segunfrancis.coolmovies.data.mapper

import com.project.segunfrancis.coolmovies.data.local.model.GenreLocal
import com.project.segunfrancis.coolmovies.domain.model.GenreDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class GenreLocalMapper @Inject constructor(): Mapper<GenreLocal, GenreDomain> {
    override fun mapDataToDomainLayer(data: GenreLocal): GenreDomain {
        return with(data) {
            GenreDomain(id, name)
        }
    }

    override fun mapDomainToDataLayer(data: GenreDomain): GenreLocal {
        return with(data) {
            GenreLocal(id, name)
        }
    }
}