package com.leskov.airport.base.extensions

import android.content.SharedPreferences

fun <T : Enum<T>> SharedPreferences.Editor.putEnum(key: String, value: T?): SharedPreferences.Editor =
    this.putInt(key, value?.ordinal ?: -1)

inline fun <reified T : Enum<T>> SharedPreferences.getEnum(key: String, default: T) =
    this.getInt(key, -1).let { if (it >= 0) enumValues<T>()[it] else default }