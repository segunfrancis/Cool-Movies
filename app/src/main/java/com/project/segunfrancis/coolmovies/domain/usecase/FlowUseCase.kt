package com.project.segunfrancis.coolmovies.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by SegunFrancis
 */

abstract class FlowUseCase<Result, in Param>(private val dispatcher: CoroutineDispatcher) {

    protected abstract fun buildFlowUseCase(params: Param? = null): Flow<Result>

    fun execute(params: Param? = null): Flow<Result> {
        return buildFlowUseCase(params).flowOn(dispatcher)
    }
}