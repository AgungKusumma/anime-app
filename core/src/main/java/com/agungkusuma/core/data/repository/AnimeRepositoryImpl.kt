package com.agungkusuma.core.data.repository

import com.agungkusuma.core.data.mapper.toDomain
import com.agungkusuma.core.data.source.remote.network.ApiService
import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimeRepositoryImpl(
    private val apiService: ApiService
) : AnimeRepository {

    override fun getAnimeList(): Flow<List<Anime>> = flow {
        val response = apiService.getAnimeList()
        val animeList = response.data.map { it.toDomain() }
        emit(animeList)
    }

    override fun getAnimeDetail(id: Int): Flow<AnimeDetail> = flow {
        val response = apiService.getAnimeDetail(id)
        val detail = response.data.toDomain()
        emit(detail)
    }
}