package com.project.segunfrancis.coolmovies.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.coolmovies.data.model.Genre
import com.project.segunfrancis.coolmovies.usecase.GetGenresRemoteUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by SegunFrancis
 */

class MainViewModel @ViewModelInject constructor(
    private val apiKey: String,
    private val dispatcher: CoroutineDispatcher,
    private val getGenresRemoteUseCase: GetGenresRemoteUseCase
) : ViewModel() {

    val temp = MutableLiveData<List<Genre>>()

    init {
        getGenresRemote()
    }

    private fun getGenresRemote() {
        viewModelScope.launch(dispatcher) {
            getGenresRemoteUseCase.execute(apiKey)
                .catch {
                    Timber.d(it.localizedMessage)
                }
                .collect {
                    temp.postValue(it.genres)
                }
        }
    }
}