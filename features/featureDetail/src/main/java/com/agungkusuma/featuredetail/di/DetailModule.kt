package com.agungkusuma.featuredetail.di

import com.agungkusuma.featuredetail.domain.usecase.GetAnimeDetailUseCase
import com.agungkusuma.featuredetail.domain.usecase.InsertAnimeUseCase
import com.agungkusuma.featuredetail.presentation.ui.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { GetAnimeDetailUseCase(get()) }
    factory { InsertAnimeUseCase(get()) }
    viewModel { DetailViewModel(get(), get()) }
}