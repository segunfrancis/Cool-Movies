package com.project.segunfrancis.coolmovies.domain.usecase

import com.project.segunfrancis.coolmovies.domain.model.ResultDomain
import com.project.segunfrancis.coolmovies.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */
class GetAllFavoritesUseCase @Inject constructor(
    private val repository: MovieRepository,
    dispatcher: CoroutineDispatcher
) :
    FlowUseCase<List<ResultDomain>, Unit>(dispatcher) {
    override fun buildFlowUseCase(params: Unit?): Flow<List<ResultDomain>> {
        return repository.getAllFavorites()
    }
}