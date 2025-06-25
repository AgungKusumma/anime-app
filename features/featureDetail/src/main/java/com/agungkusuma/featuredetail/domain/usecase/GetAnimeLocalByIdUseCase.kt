package com.agungkusuma.featuredetail.domain.usecase

import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class GetAnimeLocalByIdUseCase(
    private val repository: AnimeRepository
) {
    operator fun invoke(id: Int): Flow<Anime?> = repository.getAnimeById(id)
}