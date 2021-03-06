package com.project.segunfrancis.coolmovies.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.coolmovies.data.model.Result
import com.project.segunfrancis.coolmovies.usecase.AddFavoriteUseCase
import com.project.segunfrancis.coolmovies.usecase.GetAllFavoritesUseCase
import com.project.segunfrancis.coolmovies.usecase.RemoveFavoriteUseCase
import com.project.segunfrancis.coolmovies.util.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) : ViewModel() {

    private var _favoriteMessage = MutableLiveData<Event<FavoriteState>>()
    val favoriteMessage: LiveData<Event<FavoriteState>> get() = _favoriteMessage

    private var _allFavorites = MutableLiveData<List<Result>>()
    val allFavorites: LiveData<List<Result>> get() = _allFavorites

    init {
        getAllFavorites()
    }

    fun addFavorite(result: Result) {
        viewModelScope.launch(dispatcher) {
            addFavoriteUseCase.execute(result)
                .onCompletion {
                    _favoriteMessage.postValue(Event(FavoriteState("Added to favorites", false)))
                }
                .collect { }
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch(dispatcher) {
            removeFavoriteUseCase.execute(id)
                .onCompletion {
                    _favoriteMessage.postValue(Event(FavoriteState("Removed from favorites", true)))
                }
                .collect { }
        }
    }

    private fun getAllFavorites() {
        viewModelScope.launch(dispatcher) {
            getAllFavoritesUseCase.execute()
                .collect {
                    _allFavorites.postValue(it)
                }
        }
    }
}