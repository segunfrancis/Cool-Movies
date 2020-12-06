package com.project.segunfrancis.coolmovies.di

import com.project.segunfrancis.coolmovies.domain.repository.MovieRepository
import com.project.segunfrancis.coolmovies.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Binds
    fun movieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}