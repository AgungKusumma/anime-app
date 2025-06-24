package com.agungkusuma.animeapp.navigation

import android.os.Bundle
import com.agungkusuma.animeapp.R
import com.agungkusuma.common.navigation.BaseNavigatorImpl
import com.agungkusuma.common.navigation.FeaturesNavigation
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AppNavigatorImpl @Inject constructor() : BaseNavigatorImpl(), FeaturesNavigation {

    override fun openDetailPage(bundle: Bundle?) {
        openScreen(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}
