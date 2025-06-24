package com.agungkusuma.common.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

interface BaseNavigator {

    val navController: NavController?

    fun openScreen(@IdRes id: Int, bundle: Bundle? = null, @IdRes startDestination: Int? = null)

    fun navigateUp(): Boolean?

    fun navigateUpWithResult(fragment: Fragment, bundle: Bundle): Boolean?

    fun startDestination(@IdRes id: Int)

    fun setGraph(@NavigationRes navId: Int, @IdRes startDestination: Int, navArgument: NavArgument? = null)

    fun bind(navHostFragment: NavHostFragment)

    fun unbind()
}
