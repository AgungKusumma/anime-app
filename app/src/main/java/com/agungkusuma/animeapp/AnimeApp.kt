package com.agungkusuma.animeapp

import android.app.Application
import com.agungkusuma.animeapp.di.navigationModule
import com.agungkusuma.core.di.databaseModule
import com.agungkusuma.core.di.networkModule
import com.agungkusuma.core.di.repositoryModule
import com.agungkusuma.featuredetail.di.detailModule
import com.agungkusuma.featurehome.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AnimeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@AnimeApp)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    networkModule,
                    navigationModule,
                    homeModule,
                    detailModule
                )
            )
        }
    }
}