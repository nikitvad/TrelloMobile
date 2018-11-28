package com.nikitvad.oryanmat.trellowidget.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

const val TOKEN_KEY = "token_key"

class PreferencesUtil (application: Context) {

    private val preferences: SharedPreferences = application
            .getSharedPreferences(application.packageName, Context.MODE_PRIVATE)

    fun saveToken(string: String) {
        putString(TOKEN_KEY, string)
    }

    fun getToken(): String {
        return getString(TOKEN_KEY)
    }

    private fun getString(key: String): String {
        return preferences.getString(key, "")
    }

    private fun putInt(key: String, int: Int) {
        preferences.edit().putInt(key, int).apply()
    }

    private fun putString(key: String, string: String) {
        preferences.edit().putString(key, string).apply()
    }
}