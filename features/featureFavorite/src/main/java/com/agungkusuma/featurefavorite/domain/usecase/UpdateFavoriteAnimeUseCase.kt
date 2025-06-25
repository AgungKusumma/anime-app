package com.agungkusuma.featurefavorite.domain.usecase

import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.repository.AnimeRepository

class UpdateFavoriteAnimeUseCase(
    private val repository: AnimeRepository
) {
    operator fun invoke(anime: Anime, state: Boolean) {
        repository.updateFavoriteAnime(anime, state)
    }
}