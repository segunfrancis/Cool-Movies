package com.project.segunfrancis.coolmovies.domain.usecase

import com.project.segunfrancis.coolmovies.domain.model.GenreDomain
import com.project.segunfrancis.coolmovies.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class InsertGenreIDsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, GenreDomain>(dispatcher) {

    override fun buildFlowUseCase(params: GenreDomain?): Flow<Unit> {
        return movieRepository.insertGenreIDs(params!!)
    }
}