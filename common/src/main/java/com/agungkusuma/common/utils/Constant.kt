package com.agungkusuma.common.utils

object Constants {
    const val PREF_FILE_NAME = "Preferences"
    const val DEFAULT_TIMEOUT = 30
    const val DURATION_TIME_CLICKABLE = 500L
    const val DURATION_ANIMATION_DEFAULT = 200L

    object ContentType {
        const val APPLICATION_JSON = "application/json"
    }

    object ApiComponents {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }

    object ApiEndpoint {
        const val ANIME_LIST = "anime"
        const val ANIME_DETAIL = "anime/{id}"
    }
}
