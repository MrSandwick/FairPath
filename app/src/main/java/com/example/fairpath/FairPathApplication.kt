package com.example.fairpath

import android.app.Application
import com.example.fairpath.data.db.DatabaseProvider

class FairPathApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseProvider.initialize(this)
    }
}
