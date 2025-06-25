package com.agungkusuma.core.domain.repository

import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.model.AnimeDetail
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnimeList(): Flow<List<Anime>>
    fun getAnimeDetail(id: Int): Flow<AnimeDetail>
    fun getFavoriteAnime(): Flow<List<Anime>>
    fun getAnimeById(id: Int): Flow<Anime?>
    suspend fun insertAnime(anime: Anime)
    suspend fun deleteAnime(anime: Anime)
}