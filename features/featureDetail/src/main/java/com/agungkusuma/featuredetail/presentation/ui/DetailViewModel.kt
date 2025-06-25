package com.agungkusuma.featuredetail.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.common.utils.ActionState
import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.featuredetail.domain.usecase.GetAnimeDetailUseCase
import com.agungkusuma.featuredetail.domain.usecase.InsertAnimeUseCase
import com.agungkusuma.featuredetail.presentation.mapper.toDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getAnimeDetailUseCase: GetAnimeDetailUseCase,
    private val insertAnimeUseCase: InsertAnimeUseCase
) : ViewModel() {

    private val _animeDetailState = MutableStateFlow<ActionState>(ActionState.Loading(false))
    val animeDetailState: StateFlow<ActionState> = _animeDetailState

    private val _insertState = MutableStateFlow<ActionState>(ActionState.Loading(false))
    val insertState: StateFlow<ActionState> = _insertState

    private var cachedAnimeDetail: AnimeDetail? = null

    fun fetchAnimeDetail(id: Int) {
        viewModelScope.launch {
            getAnimeDetailUseCase(id)
                .onStart { _animeDetailState.value = ActionState.Loading(true) }
                .catch { e -> _animeDetailState.value = ActionState.Error(e.message) }
                .collect { result ->
                    cachedAnimeDetail = result
                    _animeDetailState.value = ActionState.Success(result)
                }
        }
    }

    fun insertAnime() {
        val anime = cachedAnimeDetail?.toDomainModel()?.copy(isFavorite = true) ?: return
        viewModelScope.launch {
            _insertState.value = ActionState.Loading(true)
            try {
                insertAnimeUseCase(anime)
                _insertState.value = ActionState.Success(Unit)
            } catch (e: Exception) {
                _insertState.value = ActionState.Error(e.message)
            } finally {
                _insertState.value = ActionState.Loading(false)
            }
        }
    }
}
