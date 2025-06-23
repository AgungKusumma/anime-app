package com.agungkusuma.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeDetailResponse(
    @SerializedName("data") val data: AnimeDetailItem
)

data class AnimeDetailItem(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("title_english") val titleEnglish: String?,
    @SerializedName("title_japanese") val titleJapanese: String?,
    @SerializedName("type") val type: String,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("status") val status: String,
    @SerializedName("score") val score: Double?,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("background") val background: String?,
    @SerializedName("images") val images: Images,
    @SerializedName("aired") val aired: Aired,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("themes") val themes: List<Genre>,
    @SerializedName("demographics") val demographics: List<Genre>
)
