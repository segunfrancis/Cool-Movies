package com.project.segunfrancis.coolmovies.usecase

import com.project.segunfrancis.coolmovies.data.model.Result
import com.project.segunfrancis.coolmovies.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */
class AddFavoriteUseCase @Inject constructor(
    private val repository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, Result>(dispatcher) {

    override fun buildFlowUseCase(params: Result?): Flow<Unit> {
        return repository.addFavorite(params!!)
    }
}