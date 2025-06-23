package com.agungkusuma.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    @SerializedName("pagination") val pagination: Pagination,
    @SerializedName("data") val data: List<AnimeListResponseItem>
)

data class Pagination(
    @SerializedName("last_visible_page") val lastVisiblePage: Int,
    @SerializedName("has_next_page") val hasNextPage: Boolean,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("items") val items: PaginationItems
)

data class PaginationItems(
    @SerializedName("count") val count: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("per_page") val perPage: Int
)

data class AnimeListResponseItem(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("score") val score: Double?,
    @SerializedName("aired") val aired: Aired,
    @SerializedName("images") val images: Images,
    @SerializedName("genres") val genres: List<Genre>
)

data class Aired(
    @SerializedName("from") val from: String?,
    @SerializedName("to") val to: String?,
    @SerializedName("string") val string: String
)

data class Images(
    @SerializedName("jpg") val jpg: ImageUrl,
    @SerializedName("webp") val webp: ImageUrl
)

data class ImageUrl(
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("small_image_url") val smallImageUrl: String,
    @SerializedName("large_image_url") val largeImageUrl: String
)

data class Genre(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)