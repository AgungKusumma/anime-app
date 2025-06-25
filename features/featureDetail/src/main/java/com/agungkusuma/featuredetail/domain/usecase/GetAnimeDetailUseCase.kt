package com.agungkusuma.featuredetail.domain.usecase

import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class GetAnimeDetailUseCase(
    private val repository: AnimeRepository
) {
    operator fun invoke(id: Int): Flow<AnimeDetail> = repository.getAnimeDetail(id)
}
