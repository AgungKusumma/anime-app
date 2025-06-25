package com.agungkusuma.featuredetail.domain.usecase

import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.repository.AnimeRepository

class DeleteAnimeUseCase(private val repository: AnimeRepository) {
    suspend operator fun invoke(anime: Anime) {
        repository.deleteAnime(anime)
    }
}