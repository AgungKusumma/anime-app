package com.agungkusuma.core.data.source.remote

import com.agungkusuma.core.data.source.remote.network.ApiService
import com.agungkusuma.core.data.source.remote.response.AnimeDetailResponse
import com.agungkusuma.core.data.source.remote.response.AnimeListResponse

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAnimeList(): AnimeListResponse = apiService.getAnimeList()
    suspend fun getAnimeDetail(id: Int): AnimeDetailResponse = apiService.getAnimeDetail(id)
}
