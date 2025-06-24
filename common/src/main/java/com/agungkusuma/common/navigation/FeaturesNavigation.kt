package com.agungkusuma.common.navigation

import android.os.Bundle

interface FeaturesNavigation : BaseNavigator {
    fun openDetailPage(bundle: Bundle? = null)
}