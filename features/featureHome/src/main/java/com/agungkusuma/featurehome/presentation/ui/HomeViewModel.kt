package com.agungkusuma.featurehome.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.common.utils.ActionState
import com.agungkusuma.featurehome.domain.usecase.GetAnimeListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    private val _animeState = MutableStateFlow<ActionState>(ActionState.Loading(false))
    val animeState: StateFlow<ActionState> = _animeState

    fun fetchAnimeList() {
        viewModelScope.launch {
            getAnimeListUseCase()
                .onStart { _animeState.value = ActionState.Loading(true) }
                .catch { e -> _animeState.value = ActionState.Error(e.message) }
                .collect { result ->
                    _animeState.value = ActionState.Success(result)
                }
        }
    }
}
