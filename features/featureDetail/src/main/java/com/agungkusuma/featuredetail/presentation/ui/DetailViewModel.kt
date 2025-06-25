package com.agungkusuma.featuredetail.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.common.utils.ActionState
import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.featuredetail.domain.usecase.DeleteAnimeUseCase
import com.agungkusuma.featuredetail.domain.usecase.GetAnimeDetailUseCase
import com.agungkusuma.featuredetail.domain.usecase.GetAnimeLocalByIdUseCase
import com.agungkusuma.featuredetail.domain.usecase.InsertAnimeUseCase
import com.agungkusuma.featuredetail.presentation.mapper.toDomainModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getAnimeDetailUseCase: GetAnimeDetailUseCase,
    private val getAnimeByIdUseCase: GetAnimeLocalByIdUseCase,
    private val insertAnimeUseCase: InsertAnimeUseCase,
    private val deleteAnimeUseCase: DeleteAnimeUseCase
) : ViewModel() {

    private val _animeDetailState = MutableStateFlow<ActionState>(ActionState.Loading(false))
    val animeDetailState: StateFlow<ActionState> = _animeDetailState

    private val _isFavorited = MutableStateFlow(false)
    val isFavorited: StateFlow<Boolean> = _isFavorited

    private val _toastEvent = MutableSharedFlow<Boolean>()
    val toastEvent = _toastEvent.asSharedFlow()

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

        viewModelScope.launch {
            getAnimeByIdUseCase(id).collect { localAnime ->
                _isFavorited.value = localAnime?.isFavorite == true
            }
        }
    }

    fun toggleFavorite() {
        val anime = cachedAnimeDetail?.toDomainModel() ?: return
        val isNowFavorite = !_isFavorited.value

        viewModelScope.launch {
            try {
                if (_isFavorited.value) {
                    deleteAnimeUseCase(anime)
                } else {
                    insertAnimeUseCase(anime.copy(isFavorite = true))
                }
                _toastEvent.emit(isNowFavorite)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}