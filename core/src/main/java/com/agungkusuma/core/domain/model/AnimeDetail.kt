package com.agungkusuma.core.domain.model

data class AnimeDetail(
    val id: Int,
    val title: String,
    val titleEnglish: String?,
    val titleJapanese: String?,
    val type: String,
    val episodes: Int?,
    val status: String,
    val score: Double?,
    val synopsis: String?,
    val background: String?,
    val imageUrl: String,
    val aired: String,
    val genres: List<String>,
    val themes: List<String>,
    val demographics: List<String>
)