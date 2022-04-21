package com.leskov.airport

import androidx.navigation.NavDeepLinkBuilder
import com.leskov.airport.base.activity.BaseBindingActivity
import com.leskov.airport.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main

    fun changeLanguage() {
        finishAffinity()
        NavDeepLinkBuilder(this)
            .setComponentName(this::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.mainMenuFragment)
            .createPendingIntent().send()
    }
}