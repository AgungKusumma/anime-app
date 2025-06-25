package com.agungkusuma.core.data.mapper

import com.agungkusuma.core.data.source.local.entity.AnimeEntity
import com.agungkusuma.core.domain.model.Anime

fun AnimeEntity.toDomain(): Anime =
    Anime(
        id = id,
        title = title,
        imageUrl = imageUrl,
        score = score,
        type = "",
        episodes = null,
        aired = "",
        genres = emptyList(),
        isFavorite = isFavorite
    )

fun Anime.toEntity(): AnimeEntity =
    AnimeEntity(
        id = id,
        title = title,
        imageUrl = imageUrl,
        score = score ?: 0.0,
        isFavorite = isFavorite
    )

