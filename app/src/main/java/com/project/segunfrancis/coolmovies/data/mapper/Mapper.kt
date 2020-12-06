package com.project.segunfrancis.coolmovies.data.mapper

/**
 * Created by SegunFrancis
 */

interface Mapper<I, O> {
    fun mapDataToDomainLayer(data: I): O
    fun mapDomainToDataLayer(data: O): I {
        throw UnsupportedOperationException()
    }
}