package com.example.quicksearch.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuickSearchApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}