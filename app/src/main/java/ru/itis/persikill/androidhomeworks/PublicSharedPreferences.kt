package ru.itis.persikill.androidhomeworks


import android.content.Context
import android.preference.PreferenceManager


object PublicSharedPreferences {
    fun setDefaults(key: String?, value: String?, context: Context?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun getDefaults(key: String?, context: Context?): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(key, null)
    }
}