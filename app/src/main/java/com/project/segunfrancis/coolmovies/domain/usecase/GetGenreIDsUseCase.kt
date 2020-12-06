package com.project.segunfrancis.coolmovies.domain.usecase

import com.project.segunfrancis.coolmovies.domain.model.GenreDomain
import com.project.segunfrancis.coolmovies.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class GetGenreIDsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<List<GenreDomain>, Unit>(dispatcher) {

    override fun buildFlowUseCase(params: Unit?): Flow<List<GenreDomain>> {
        return movieRepository.getGenreLocalIDs()
    }
}