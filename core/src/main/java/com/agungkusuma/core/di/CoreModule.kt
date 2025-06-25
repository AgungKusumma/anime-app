package com.agungkusuma.core.di

import com.agungkusuma.core.data.repository.AnimeRepositoryImpl
import com.agungkusuma.core.domain.repository.AnimeRepository
import org.koin.dsl.module

val coreModule = module {
    single<AnimeRepository> { AnimeRepositoryImpl(get()) }
}
