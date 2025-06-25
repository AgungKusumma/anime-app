package com.agungkusuma.core.data.source.local

import com.agungkusuma.core.data.source.local.entity.AnimeEntity
import com.agungkusuma.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val animeDao: AnimeDao) {

    fun getFavoriteAnime(): Flow<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    suspend fun insertAnime(anime: AnimeEntity) = animeDao.insertAnime(anime)

    fun updateFavoriteAnime(anime: AnimeEntity, newState: Boolean) {
        anime.isFavorite = newState
        animeDao.updateFavoriteAnime(anime)
    }
}
