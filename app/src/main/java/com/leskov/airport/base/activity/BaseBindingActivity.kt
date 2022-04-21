package com.leskov.airport.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDeepLinkBuilder
import com.leskov.airport.MainActivity
import com.leskov.airport.R
import com.leskov.airport.base.utils.helper.SharedPreferenceManager
import com.leskov.airport.domain.model.Languages
import java.util.*

abstract class BaseBindingActivity<Binding: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: Binding

    private lateinit var sharedPreferencesManager: SharedPreferenceManager

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferencesManager = SharedPreferenceManager(this)
        setDefaultLanguage()

        binding = DataBindingUtil.setContentView(this, layoutId)
        setContentView(binding.root)
    }

    private fun setDefaultLanguage() {
        if (sharedPreferencesManager.hasLanguage()) {
            setLocal(sharedPreferencesManager.language)
        } else {
            val lang = when (Locale.getDefault().language) {
                "uk" -> Languages.UKR
                else -> Languages.ENG
            }
            sharedPreferencesManager.language = lang
        }
    }

    @Suppress("DEPRECATION")
    fun setLocal(lang: Languages) {
        val languageStr =
            when (lang) {
                Languages.ENG -> "ru"
                Languages.UKR -> "uk"
            }
        val myLocale = Locale(languageStr)
        val conf = resources.configuration
        conf.setLocale(myLocale)
        resources.updateConfiguration(conf, resources.displayMetrics)
    }
}