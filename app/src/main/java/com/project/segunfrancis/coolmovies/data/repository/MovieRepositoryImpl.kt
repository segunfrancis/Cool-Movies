package com.project.segunfrancis.coolmovies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.project.segunfrancis.coolmovies.data.local.db.MovieDatabase
import com.project.segunfrancis.coolmovies.data.mapper.GenreLocalMapper
import com.project.segunfrancis.coolmovies.data.mapper.GenreMapper
import com.project.segunfrancis.coolmovies.data.mapper.ResultMapper
import com.project.segunfrancis.coolmovies.data.mapper.ResultLocalMapper
import com.project.segunfrancis.coolmovies.data.remote.api.MovieApi
import com.project.segunfrancis.coolmovies.data.remote.source.MoviePagingSource
import com.project.segunfrancis.coolmovies.domain.model.GenreDomain
import com.project.segunfrancis.coolmovies.domain.model.GenreResponseDomain
import com.project.segunfrancis.coolmovies.domain.model.ResultDomain
import com.project.segunfrancis.coolmovies.domain.repository.MovieRepository
import com.project.segunfrancis.coolmovies.util.AppConstants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val db: MovieDatabase,
    private val resultMapper: ResultMapper,
    private val genreMapper: GenreMapper,
    private val resultLocalMapper: ResultLocalMapper,
    private val genreLocalMapper: GenreLocalMapper
) : MovieRepository {
    override fun getTopRatedMovies(apiKey: String): Flow<PagingData<ResultDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                MoviePagingSource(api, apiKey)
            }).flow.map { pagingData ->
            pagingData.map {
                resultMapper.mapDataToDomainLayer(it)
            }
        }
    }

    override fun addFavorite(result: ResultDomain): Flow<Unit> {
        return flow {
            emit(db.movieDao().addFavorite(resultLocalMapper.mapDomainToDataLayer(result)))
        }
    }


    override fun removeFavorite(id: Int): Flow<Unit> {
        return flow {
            emit(db.movieDao().removeFavorite(id))
        }
    }

    override fun getAllFavorites(): Flow<List<ResultDomain>> {
        return db.movieDao().getAllFavorites().map { results ->
            results.map { local ->
                resultLocalMapper.mapDataToDomainLayer(local)
            }
        }
    }

    override fun getGenresRemote(apiKey: String): Flow<GenreResponseDomain> {
        return flow {
            emit(genreMapper.mapDataToDomainLayer(api.getGenres(apiKey)))
        }
    }

    override fun insertGenreIDs(genre: GenreDomain): Flow<Unit> {
        return flow {
            emit(db.movieDao().insertGenreIDs(genreLocalMapper.mapDomainToDataLayer(genre)))
        }
    }

    override fun getGenreLocalIDs(): Flow<List<GenreDomain>> {
        return db.movieDao().getGenreIDs().map { genres ->
            genres.map {
                genreLocalMapper.mapDataToDomainLayer(it)
            }
        }
    }
}