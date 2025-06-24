package com.agungkusuma.featurehome.domain.usecase

import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<List<Anime>> = repository.getAnimeList()
}