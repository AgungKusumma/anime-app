package com.agungkusuma.featuredetail.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.common.utils.ActionState
import com.agungkusuma.featuredetail.domain.usecase.GetAnimeDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getAnimeDetailUseCase: GetAnimeDetailUseCase
) : ViewModel() {

    private val _animeDetailState = MutableStateFlow<ActionState>(ActionState.Loading(false))
    val animeDetailState: StateFlow<ActionState> = _animeDetailState

    fun fetchAnimeDetail(id: Int) {
        viewModelScope.launch {
            getAnimeDetailUseCase(id)
                .onStart { _animeDetailState.value = ActionState.Loading(true) }
                .catch { e -> _animeDetailState.value = ActionState.Error(e.message) }
                .collect { result ->
                    _animeDetailState.value = ActionState.Success(result)
                }
        }
    }
}
