package com.agungkusuma.featuredetail.domain.usecase

import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeDetailUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(id: Int): Flow<AnimeDetail> = repository.getAnimeDetail(id)
}
