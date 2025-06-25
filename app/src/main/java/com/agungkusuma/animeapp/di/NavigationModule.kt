package com.agungkusuma.animeapp.di

import com.agungkusuma.animeapp.navigation.AppNavigatorImpl
import com.agungkusuma.common.navigation.BaseNavigator
import com.agungkusuma.common.navigation.FeaturesNavigation
import org.koin.dsl.module

val navigationModule = module {
    single<BaseNavigator> { AppNavigatorImpl() }
    single<FeaturesNavigation> { get<BaseNavigator>() as FeaturesNavigation }
}
