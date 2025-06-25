package com.agungkusuma.featuredetail.di

import com.agungkusuma.featuredetail.presentation.ui.DetailViewModel
import com.agungkusuma.featuredetail.domain.usecase.DeleteAnimeUseCase
import com.agungkusuma.featuredetail.domain.usecase.GetAnimeDetailUseCase
import com.agungkusuma.featuredetail.domain.usecase.GetAnimeLocalByIdUseCase
import com.agungkusuma.featuredetail.domain.usecase.InsertAnimeUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { GetAnimeDetailUseCase(get()) }
    factory { GetAnimeLocalByIdUseCase(get()) }
    factory { InsertAnimeUseCase(get()) }
    factory { DeleteAnimeUseCase(get()) }
    viewModel { DetailViewModel(get(), get(), get(), get()) }
}