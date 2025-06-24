package com.agungkusuma.core.domain.repository

import com.agungkusuma.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnimeList(): Flow<List<Anime>>
}