package com.project.segunfrancis.coolmovies.usecase

import com.project.segunfrancis.coolmovies.data.model.Result
import com.project.segunfrancis.coolmovies.repository.MovieRepository
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
    FlowUseCase<List<Result>, Unit>(dispatcher) {
    override fun buildFlowUseCase(params: Unit?): Flow<List<Result>> {
        return repository.getAllFavorites()
    }
}