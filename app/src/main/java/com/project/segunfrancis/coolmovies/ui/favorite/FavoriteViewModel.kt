package com.project.segunfrancis.coolmovies.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.coolmovies.data.model.Result
import com.project.segunfrancis.coolmovies.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _favoriteMessage = MutableLiveData<String>()
    val favoriteMessage: LiveData<String> get() = _favoriteMessage

    private var _allFavorites = MutableLiveData<List<Result>>()
    val allFavorites: LiveData<List<Result>> get() = _allFavorites

    init {
        getAllFavorites()
    }

    fun addFavorite(result: Result) {
        viewModelScope.launch(dispatcher) {
            repository.addFavorite(result)
                .onCompletion {
                    _favoriteMessage.postValue("Added to favorites")
                }
                .collect { }
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch(dispatcher) {
            repository.removeFavorite(id)
                .onCompletion {
                    _favoriteMessage.postValue("Removed from favorites")
                }
                .collect { }
        }
    }

    private fun getAllFavorites() {
        viewModelScope.launch(dispatcher) {
            repository.getAllFavorites()
                .collect {
                    _allFavorites.postValue(it)
                }
        }
    }
}