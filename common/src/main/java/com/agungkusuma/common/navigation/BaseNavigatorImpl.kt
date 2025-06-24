package com.agungkusuma.common.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import java.lang.ref.WeakReference

abstract class BaseNavigatorImpl : BaseNavigator {

    private var navHostFragment: NavHostFragment? = null
    override var navController: NavController? = null

    override fun openScreen(@IdRes id: Int, bundle: Bundle?, @IdRes startDestination: Int?) {
        navController?.navigateSafe(id, bundle)

        if (startDestination != null) {
            navHostFragment?.childFragmentManager?.let {
                it.beginTransaction().remove(it.fragments[it.fragments.size - 1]).commit()
            }
            navController?.graph?.setStartDestination(startDestination)
        }
    }

    override fun navigateUp(): Boolean? = navController?.navigateUp()

    override fun navigateUpWithResult(fragment: Fragment, bundle: Bundle): Boolean? {
        fragment.setFragmentResult("result_key", bundle)
        return navController?.navigateUp()
    }

    override fun startDestination(@IdRes id: Int) {
        navController?.graph?.let {
            it.setStartDestination(id)
            navController?.graph = it
        }
    }

    override fun setGraph(navId: Int, startDestination: Int, navArgument: NavArgument?) {
        val navGraph = navController?.navInflater?.inflate(navId)
        navGraph?.setStartDestination(startDestination)
        navArgument?.let {
            navGraph?.addArgument("data", navArgument)
        }
        navGraph?.let { navController?.graph = it }
    }

    override fun bind(navHostFragment: NavHostFragment) {
        this.navController = WeakReference(navHostFragment.navController).get()
        this.navHostFragment = navHostFragment
    }

    override fun unbind() {
        navController = null
    }

    private fun NavController.navigateSafe(
        @IdRes resId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null
    ) {
        val destinationId = currentDestination?.getAction(resId)?.destinationId ?: return
        currentDestination?.let { node ->
            val currentNode = when (node) {
                is NavGraph -> node
                else -> node.parent
            }
            currentNode?.findNode(destinationId)?.let { navigate(resId, args, navOptions) }
        }
    }
}
