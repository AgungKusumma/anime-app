package com.agungkusuma.animeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.agungkusuma.common.navigation.BaseNavigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val baseNavigator: BaseNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        baseNavigator.bind(navHostFragment)
    }

    override fun onDestroy() {
        baseNavigator.unbind()
        super.onDestroy()
    }
}