package com.agungkusuma.featurehome.domain.usecase

import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class GetAnimeListUseCase(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<List<Anime>> = repository.getAnimeList()
}