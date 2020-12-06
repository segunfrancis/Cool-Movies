package com.project.segunfrancis.coolmovies.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.coolmovies.domain.model.GenreDomain
import com.project.segunfrancis.coolmovies.domain.usecase.GetGenreIDsUseCase
import com.project.segunfrancis.coolmovies.domain.usecase.GetGenresRemoteUseCase
import com.project.segunfrancis.coolmovies.domain.usecase.InsertGenreIDsUseCase
import com.project.segunfrancis.coolmovies.ui.mapper.GenreMapper
import com.project.segunfrancis.coolmovies.ui.model.Genre
import com.project.segunfrancis.coolmovies.util.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

/**
 * Created by SegunFrancis
 */

class MainViewModel @ViewModelInject constructor(
    private val apiKey: String,
    private val dispatcher: CoroutineDispatcher,
    private val getGenresRemoteUseCase: GetGenresRemoteUseCase,
    private val getGenreIDsUseCase: GetGenreIDsUseCase,
    private val insertGenreIDsUseCase: InsertGenreIDsUseCase,
    private val genreMapper: GenreMapper
) : ViewModel() {

    val genreIDs = MutableLiveData<State<List<Genre>>>()

    init {
        getGenreIDsLocal()
    }

    private fun getGenresRemote() {
        genreIDs.postValue(State.Loading)
        viewModelScope.launch(dispatcher) {
            getGenresRemoteUseCase.execute(apiKey)
                .catch {
                    genreIDs.postValue(State.Error(it))
                }
                .collect {
                    for (genre in it.genres)
                        insertGenreIDs(genre)
                }
        }
    }

    private fun insertGenreIDs(genre: GenreDomain) {
        viewModelScope.launch(dispatcher) {
            insertGenreIDsUseCase.execute(genre)
                .catch {
                    genreIDs.postValue(State.Error(it))
                }
                .onCompletion {
                    getGenreIDsLocal()
                }
                .collect { }
        }
    }

    private fun getGenreIDsLocal() {
        viewModelScope.launch(dispatcher) {
            getGenreIDsUseCase.execute()
                .catch {
                    genreIDs.postValue(State.Error(it))
                }
                .collect { genres ->
                    if (genres.isEmpty())
                        getGenresRemote()
                    else
                        genreIDs.postValue(State.Success(genres.map {
                            genreMapper.mapDomainToDataLayer(it)
                        }))
                }
        }
    }
}