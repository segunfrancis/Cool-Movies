package com.project.segunfrancis.coolmovies.ui.mapper

/**
 * Created by SegunFrancis
 */

interface Mapper<I, O> {
    fun mapDomainToDataLayer(data: I): O
    fun mapDataToDomainLayer(data: O): I {
        throw UnsupportedOperationException()
    }
}