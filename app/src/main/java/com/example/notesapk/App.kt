package com.example.notesapk

import android.app.Application
import com.example.notesapk.utils.PreferenceHelper

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        var sheredPreferences = PreferenceHelper()
        sheredPreferences.unit(this)
    }
}