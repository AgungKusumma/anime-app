package com.agungkusuma.animeapp.di

import com.agungkusuma.animeapp.navigation.AppNavigatorImpl
import com.agungkusuma.common.navigation.BaseNavigator
import com.agungkusuma.common.navigation.FeaturesNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

    @Binds
    @ActivityScoped
    abstract fun bindBaseNavigator(navigator: AppNavigatorImpl): BaseNavigator

    @Binds
    @ActivityScoped
    abstract fun bindFeaturesNavigation(navigator: AppNavigatorImpl): FeaturesNavigation
}
