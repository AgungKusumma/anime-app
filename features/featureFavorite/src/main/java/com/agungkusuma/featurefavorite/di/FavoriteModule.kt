package com.agungkusuma.featurefavorite.di

import com.agungkusuma.featurefavorite.domain.usecase.GetFavoriteAnimeUseCase
import com.agungkusuma.featurefavorite.presentation.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
    factory { GetFavoriteAnimeUseCase(get()) }
}
