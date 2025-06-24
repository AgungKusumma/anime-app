package com.agungkusuma.core.data.mapper

import com.agungkusuma.core.data.source.remote.response.AnimeDetailItem
import com.agungkusuma.core.domain.model.AnimeDetail

fun AnimeDetailItem.toDomain(): AnimeDetail {
    return AnimeDetail(
        id = id,
        title = title,
        titleEnglish = titleEnglish,
        titleJapanese = titleJapanese,
        type = type,
        episodes = episodes,
        status = status,
        score = score,
        synopsis = synopsis,
        background = background,
        imageUrl = images.webp.imageUrl,
        aired = aired.string,
        genres = genres.map { it.name },
        themes = themes.map { it.name },
        demographics = demographics.map { it.name }
    )
}
