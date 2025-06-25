package com.agungkusuma.core.data.repository

import com.agungkusuma.core.data.mapper.toDomain
import com.agungkusuma.core.data.mapper.toEntity
import com.agungkusuma.core.data.source.local.LocalDataSource
import com.agungkusuma.core.data.source.remote.RemoteDataSource
import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AnimeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : AnimeRepository {

    override fun getAnimeList(): Flow<List<Anime>> = flow {
        val response = remoteDataSource.getAnimeList()
        val animeList = response.data.map { it.toDomain() }
        emit(animeList)
    }

    override fun getAnimeDetail(id: Int): Flow<AnimeDetail> = flow {
        val response = remoteDataSource.getAnimeDetail(id)
        val detail = response.data.toDomain()
        emit(detail)
    }

    override fun getFavoriteAnime(): Flow<List<Anime>> =
        localDataSource.getFavoriteAnime().map { list ->
            list.map { it.toDomain() }
        }

    override fun getAnimeById(id: Int): Flow<Anime?> {
        return localDataSource.getAnimeById(id).map { it?.toDomain() }
    }

    override suspend fun insertAnime(anime: Anime) {
        val entity = anime.toEntity()
        localDataSource.insertAnime(entity)
    }
}