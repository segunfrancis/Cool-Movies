package com.project.segunfrancis.coolmovies.ui.favorite

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nhaarman.mockitokotlin2.mock
import com.project.segunfrancis.coolmovies.MainCoroutineRule
import com.project.segunfrancis.coolmovies.TestSetup.database
import com.project.segunfrancis.coolmovies.TestSetup.result
import com.project.segunfrancis.coolmovies.data.local.db.MovieDatabase
import com.project.segunfrancis.coolmovies.data.remote.api.MovieApi
import com.project.segunfrancis.coolmovies.repository.MovieRepository
import com.project.segunfrancis.coolmovies.repository.MovieRepositoryImpl
import com.project.segunfrancis.coolmovies.usecase.AddFavoriteUseCase
import com.project.segunfrancis.coolmovies.usecase.GetAllFavoritesUseCase
import com.project.segunfrancis.coolmovies.usecase.RemoveFavoriteUseCase
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by SegunFrancis
 */

@RunWith(AndroidJUnit4::class)
class FavoriteViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val db: MovieDatabase = database(context)
    private val dispatcher = coroutineRule.testDispatcher
    private val api: MovieApi = mock()
    private val repository: MovieRepository = MovieRepositoryImpl(api, db, dispatcher)
    private val result = result()


    @Test
    fun test_to_add_movie_to_favorite() {
        val getAllFavoritesUseCase = GetAllFavoritesUseCase(repository, dispatcher)
        val addFavoritesUseCase = AddFavoriteUseCase(repository, dispatcher)
        val removeFavoriteUseCase = RemoveFavoriteUseCase(repository, dispatcher)
        val viewModel = FavoriteViewModel(
            coroutineRule.testDispatcher,
            getAllFavoritesUseCase,
            addFavoritesUseCase,
            removeFavoriteUseCase
        )

        viewModel.addFavorite(result)
        viewModel.allFavorites.observeForever {
            assertNotNull(it)
        }
    }
}