package com.project.segunfrancis.coolmovies.data.remote.source

import androidx.paging.PagingSource
import com.project.segunfrancis.coolmovies.data.remote.api.MovieApi
import com.project.segunfrancis.coolmovies.data.remote.model.Result
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class NowPlayingPagingSource @Inject constructor(private val api: MovieApi, private val apiKey: String) :
    PagingSource<Int, Result>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.getNowPlaying(apiKey, position)
            val movies = response.results
            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (t: Throwable) {
            return LoadResult.Error(t)
        }
    }
}