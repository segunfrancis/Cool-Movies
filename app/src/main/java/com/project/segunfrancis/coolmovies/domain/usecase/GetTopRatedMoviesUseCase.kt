package com.project.segunfrancis.coolmovies.domain.usecase

import androidx.paging.PagingData
import com.project.segunfrancis.coolmovies.domain.model.ResultDomain
import com.project.segunfrancis.coolmovies.domain.repository.MovieRepository
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
    FlowUseCase<PagingData<ResultDomain>, String>(dispatcher) {

    override fun buildFlowUseCase(params: String?): Flow<PagingData<ResultDomain>> {
        return repository.getTopRatedMovies(params!!)
    }
}