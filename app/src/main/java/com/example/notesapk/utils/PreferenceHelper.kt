package com.example.notesapk.utils
import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private var sheredPreferences: SharedPreferences? = null

    fun unit(context: Context){
      sheredPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var onBoard: Boolean
        get() = sheredPreferences!!.getBoolean("onBoard", false)
        set(value) = sheredPreferences!!.edit().putBoolean("onBoard", value).apply()
}