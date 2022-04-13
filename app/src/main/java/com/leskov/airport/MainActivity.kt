package com.leskov.airport

import com.leskov.airport.base.activity.BaseBindingActivity
import com.leskov.airport.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main

}