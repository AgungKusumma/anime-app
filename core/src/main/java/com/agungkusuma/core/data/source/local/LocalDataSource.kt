package com.agungkusuma.core.data.source.local

import com.agungkusuma.core.data.source.local.entity.AnimeEntity
import com.agungkusuma.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val animeDao: AnimeDao) {

    fun getFavoriteAnime(): Flow<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    fun getAnimeById(id: Int): Flow<AnimeEntity?> = animeDao.getAnimeById(id)

    suspend fun insertAnime(anime: AnimeEntity) = animeDao.insertAnime(anime)

    suspend fun deleteAnime(anime: AnimeEntity) = animeDao.deleteAnime(anime)
}
