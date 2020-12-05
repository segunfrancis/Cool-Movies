package com.project.segunfrancis.coolmovies.usecase

import com.project.segunfrancis.coolmovies.data.model.GenreResponse
import com.project.segunfrancis.coolmovies.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class GetGenresRemoteUseCase @Inject constructor(
    private val repository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<GenreResponse, String>(dispatcher) {

    override fun buildFlowUseCase(params: String?): Flow<GenreResponse> {
        return repository.getGenresRemote(params!!)
    }
}