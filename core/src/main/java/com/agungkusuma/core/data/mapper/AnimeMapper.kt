package com.agungkusuma.core.data.mapper

import com.agungkusuma.core.data.source.remote.response.AnimeListResponseItem
import com.agungkusuma.core.domain.model.Anime

fun AnimeListResponseItem.toDomain(): Anime {
    return Anime(
        id = this.id,
        title = this.title,
        imageUrl = this.images.jpg.imageUrl,
        score = this.score,
        type = this.type,
        episodes = this.episodes,
        aired = this.aired.string,
        genres = this.genres.map { it.name }
    )
}
