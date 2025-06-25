package com.agungkusuma.featuredetail.di

import DetailViewModel
import com.agungkusuma.featuredetail.domain.usecase.GetAnimeDetailUseCase
import com.agungkusuma.featuredetail.domain.usecase.GetAnimeLocalByIdUseCase
import com.agungkusuma.featuredetail.domain.usecase.InsertAnimeUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { GetAnimeDetailUseCase(get()) }
    factory { GetAnimeLocalByIdUseCase(get()) }
    factory { InsertAnimeUseCase(get()) }
    viewModel { DetailViewModel(get(), get(), get()) }
}