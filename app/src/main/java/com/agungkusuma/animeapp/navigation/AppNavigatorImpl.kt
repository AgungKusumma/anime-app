package com.agungkusuma.animeapp.navigation

import android.os.Bundle
import com.agungkusuma.animeapp.R
import com.agungkusuma.common.navigation.BaseNavigatorImpl
import com.agungkusuma.common.navigation.FeaturesNavigation

class AppNavigatorImpl : BaseNavigatorImpl(), FeaturesNavigation {

    override fun openDetailPage(bundle: Bundle?) {
        openScreen(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    override fun openFavoritePage(bundle: Bundle?) {
        openActivity(
            className = "com.agungkusuma.featurefavorite.presentation.ui.FavoriteActivity",
            bundle = bundle
        )
    }
}
