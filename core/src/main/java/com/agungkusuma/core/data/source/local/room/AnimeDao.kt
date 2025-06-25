package com.agungkusuma.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agungkusuma.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime WHERE isFavorite = 1")
    fun getFavoriteAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE id = :id LIMIT 1")
    fun getAnimeById(id: Int): Flow<AnimeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)
}
