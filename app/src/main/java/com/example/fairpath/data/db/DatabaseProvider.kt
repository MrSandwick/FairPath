package com.example.fairpath.data.db

import android.content.Context
import com.example.fairpath.data.ContactRepository
import kotlin.concurrent.Volatile

object DatabaseProvider {
    @Volatile
    private var database: FairPathDatabase? = null

    @Volatile
    private var repository: ContactRepository? = null

    fun initialize(context: Context) {
        if (database == null) {
            synchronized(this) {
                if (database == null) {
                    database = FairPathDatabase.getInstance(context)
                    repository = ContactRepository(database!!.contactDao())
                }
            }
        }
    }

    fun getRepository(): ContactRepository {
        return repository ?: throw IllegalStateException(
            "DatabaseProvider not initialized. Call DatabaseProvider.initialize(context) in your Application.onCreate()"
        )
    }

    fun getDatabase(): FairPathDatabase {
        return database ?: throw IllegalStateException(
            "DatabaseProvider not initialized. Call DatabaseProvider.initialize(context) in your Application.onCreate()"
        )
    }
}
