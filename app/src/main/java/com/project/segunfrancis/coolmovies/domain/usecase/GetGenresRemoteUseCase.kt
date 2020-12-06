package com.project.segunfrancis.coolmovies.domain.usecase

import com.project.segunfrancis.coolmovies.domain.model.GenreResponseDomain
import com.project.segunfrancis.coolmovies.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class GetGenresRemoteUseCase @Inject constructor(
    private val repository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : FlowUseCase<GenreResponseDomain, String>(dispatcher) {

    override fun buildFlowUseCase(params: String?): Flow<GenreResponseDomain> {
        return repository.getGenresRemote(params!!)
    }
}