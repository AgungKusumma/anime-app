package com.agungkusuma.core.data.source.remote.network

import com.agungkusuma.core.data.source.remote.response.AnimeDetailResponse
import com.agungkusuma.core.data.source.remote.response.AnimeListResponse
import com.agungkusuma.core.utils.Constants.ApiEndpoint.ANIME_DETAIL
import com.agungkusuma.core.utils.Constants.ApiEndpoint.ANIME_LIST
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(ANIME_LIST)
    suspend fun getAnimeList(): AnimeListResponse

    @GET(ANIME_DETAIL)
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): AnimeDetailResponse
}
