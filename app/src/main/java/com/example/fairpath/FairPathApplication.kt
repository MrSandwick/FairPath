package com.example.fairpath

import android.app.Application
import com.example.fairpath.data.ContactRepository
import com.example.fairpath.data.db.FairPathDatabase

class FairPathApplication : Application() {
    val database by lazy { FairPathDatabase.getInstance(this) }
    val contactRepository by lazy { ContactRepository(database.contactDao()) }
}
