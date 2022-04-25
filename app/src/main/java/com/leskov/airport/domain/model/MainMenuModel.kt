package com.leskov.airport.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MainMenuModel(val title: String, @StringRes val titleRes: Int, @DrawableRes val icon: Int)