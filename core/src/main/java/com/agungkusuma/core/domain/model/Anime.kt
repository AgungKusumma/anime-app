package com.agungkusuma.core.domain.model

data class Anime(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val score: Double?,
    val type: String,
    val episodes: Int?,
    val aired: String,
    val genres: List<String>
)