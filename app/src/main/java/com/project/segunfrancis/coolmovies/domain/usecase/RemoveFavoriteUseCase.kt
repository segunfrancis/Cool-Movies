package com.project.segunfrancis.coolmovies.domain.usecase

import com.project.segunfrancis.coolmovies.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Int>(dispatcher) {

    override fun buildFlowUseCase(params: Int?): Flow<Unit> {
        return repository.removeFavorite(params!!)
    }
}