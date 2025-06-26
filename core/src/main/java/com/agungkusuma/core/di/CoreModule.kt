package com.agungkusuma.core.di

import android.app.Application
import androidx.room.Room
import com.agungkusuma.core.data.repository.AnimeRepositoryImpl
import com.agungkusuma.core.data.source.local.LocalDataSource
import com.agungkusuma.core.data.source.local.room.AnimeDao
import com.agungkusuma.core.data.source.local.room.AnimeDatabase
import com.agungkusuma.core.data.source.remote.RemoteDataSource
import com.agungkusuma.core.domain.repository.AnimeRepository
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get<Application>(),
            AnimeDatabase::class.java,
            "Anime.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single<AnimeDao> { get<AnimeDatabase>().animeDao() }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<AnimeRepository> {
        AnimeRepositoryImpl(
            get(),
            get(),
        )
    }
}

