package com.project.segunfrancis.coolmovies.usecase

import androidx.paging.PagingData
import com.project.segunfrancis.coolmovies.data.model.Result
import com.project.segunfrancis.coolmovies.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    dispatcher: CoroutineDispatcher
) :
    FlowUseCase<PagingData<Result>, String>(dispatcher) {

    override fun buildFlowUseCase(params: String?): Flow<PagingData<Result>> {
        return repository.getTopRatedMovies(params!!)
    }
}