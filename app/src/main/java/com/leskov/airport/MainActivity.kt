package com.leskov.airport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leskov.airport.base.BaseBindingActivity
import com.leskov.airport.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main
}