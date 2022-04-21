package com.leskov.airport.base.utils.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.leskov.airport.base.extensions.getEnum
import com.leskov.airport.base.extensions.putEnum
import com.leskov.airport.domain.model.Languages

@SuppressLint("CommitPrefEdits")
class SharedPreferenceManager constructor(val context: Context) {

    private val PREFERENCES_FILE_NAME = "airport.spref"
    private val CURRENT_LANG = "current_lang"
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        this.sharedPreferences =
            context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
        this.editor = sharedPreferences.edit()
    }

    fun hasLanguage() = sharedPreferences.contains(CURRENT_LANG)

    var language: Languages
        get() {
            return sharedPreferences.getEnum(CURRENT_LANG, Languages.ENG)
        }
        set(language) {
            editor.putEnum(CURRENT_LANG, language)
            editor.apply()
        }

    fun clearAllSharedPreferences() {
        sharedPreferences.edit()
            ?.clear()
            ?.apply()
    }
}