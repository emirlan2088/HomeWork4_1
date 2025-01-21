package com.example.notesapk

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapk.data.db.AppDataBase
import com.example.notesapk.utils.PreferenceHelper


class App: Application() {

    companion object{
        var appDataBase: AppDataBase? = null

    }

    override fun onCreate() {
        super.onCreate()
        var sheredPreferences = PreferenceHelper()
        sheredPreferences.unit(this)
        getInstance()
    }

    private fun getInstance(): AppDataBase? {

        if (appDataBase == null){
            appDataBase = applicationContext.let {context ->
                Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

            }
        }
        return appDataBase
    }
}