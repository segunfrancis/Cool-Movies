package com.project.segunfrancis.coolmovies.domain.usecase

import com.project.segunfrancis.coolmovies.domain.model.ResultDomain
import com.project.segunfrancis.coolmovies.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */
class AddFavoriteUseCase @Inject constructor(
    private val repository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, ResultDomain>(dispatcher) {

    override fun buildFlowUseCase(params: ResultDomain?): Flow<Unit> {
        return repository.addFavorite(params!!)
    }
}