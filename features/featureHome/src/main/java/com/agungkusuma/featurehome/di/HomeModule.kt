package com.agungkusuma.featurehome.di

import com.agungkusuma.featurehome.domain.usecase.GetAnimeListUseCase
import com.agungkusuma.featurehome.presentation.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { GetAnimeListUseCase(get()) }
    viewModel { HomeViewModel(get()) }
}
