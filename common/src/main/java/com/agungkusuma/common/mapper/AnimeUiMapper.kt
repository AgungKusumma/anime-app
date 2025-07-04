package com.agungkusuma.common.mapper

import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.common.model.AnimeUiModel

fun Anime.toUi(): AnimeUiModel {
    return AnimeUiModel(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        score = this.score?.toString() ?: "-",
        type = this.type,
        aired = this.aired
    )
}
