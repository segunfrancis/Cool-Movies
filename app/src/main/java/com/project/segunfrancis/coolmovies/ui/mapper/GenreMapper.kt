package com.project.segunfrancis.coolmovies.ui.mapper

import com.project.segunfrancis.coolmovies.domain.model.GenreDomain
import com.project.segunfrancis.coolmovies.ui.model.Genre
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class GenreMapper @Inject constructor(): Mapper<GenreDomain, Genre>{
    override fun mapDomainToDataLayer(data: GenreDomain): Genre {
        return with(data) {
            Genre(id, name)
        }
    }
}