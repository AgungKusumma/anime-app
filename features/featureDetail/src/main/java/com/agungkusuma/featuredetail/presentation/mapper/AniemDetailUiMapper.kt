package com.agungkusuma.featuredetail.presentation.mapper

import com.agungkusuma.core.domain.model.AnimeDetail
import com.agungkusuma.featuredetail.presentation.model.AnimeDetailUiModel

fun AnimeDetail.toUiModel(): AnimeDetailUiModel {
    return AnimeDetailUiModel(
        title = titleEnglish ?: titleJapanese ?: title,
        imageUrl = imageUrl,
        typeAndEpisodes = "$type - ${episodes ?: "?"} eps",
        score = score?.toString() ?: "-",
        synopsis = synopsis ?: "No synopsis available.",
        genres = genres.joinToString(),
        aired = aired
    )
}
