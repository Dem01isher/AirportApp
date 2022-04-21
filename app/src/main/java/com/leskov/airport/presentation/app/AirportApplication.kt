package com.leskov.airport.presentation.app

import android.app.Application
import android.content.Context
import com.leskov.airport.base.utils.helper.LocalizeHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AirportApplication : Application(){
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocalizeHelper.onAttach(newBase))
    }
}