package com.agungkusuma.featuredetail.presentation.mapper

import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.featuredetail.presentation.model.AnimeDetailUiModel

fun AnimeDetail.toUiModel(): AnimeDetailUiModel {
    return AnimeDetailUiModel(
        title = title,
        imageUrl = imageUrl,
        typeAndEpisodes = "$type - ${episodes ?: "?"} eps",
        score = score?.toString() ?: "-",
        synopsis = synopsis ?: "No synopsis available.",
        genres = genres.joinToString(),
        aired = aired
    )
}

fun AnimeDetail.toDomainModel(): Anime {
    return Anime(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        score = this.score,
        type = this.type,
        aired = this.aired,
        episodes = this.episodes,
        genres = this.genres,
        isFavorite = this.isFavorite
    )
}

