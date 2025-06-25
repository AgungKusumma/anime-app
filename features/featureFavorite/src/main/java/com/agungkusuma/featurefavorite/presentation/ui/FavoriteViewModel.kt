package com.agungkusuma.featurefavorite.presentation.ui

import com.agungkusuma.featurefavorite.domain.usecase.GetFavoriteAnimeUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agungkusuma.core.domain.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavoriteAnimeUseCase: GetFavoriteAnimeUseCase
) : ViewModel() {

    private val _favoriteAnime = MutableStateFlow<List<Anime>>(emptyList())
    val favoriteAnime: StateFlow<List<Anime>> = _favoriteAnime

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getFavoriteAnimeUseCase().collect {
                _favoriteAnime.value = it
            }
        }
    }
}