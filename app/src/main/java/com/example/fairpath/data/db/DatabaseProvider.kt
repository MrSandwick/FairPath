package com.example.fairpath.data.db

import android.content.Context
import com.example.fairpath.data.ContactRepository
import com.example.fairpath.data.SignatureRepository
import kotlin.concurrent.Volatile

object DatabaseProvider {
    @Volatile
    private var database: FairPathDatabase? = null

    @Volatile
    private var repository: ContactRepository? = null

    @Volatile
    private var signatureRepo: SignatureRepository? = null

    fun initialize(context: Context) {
        if (database == null) {
            synchronized(this) {
                if (database == null) {
                    database = FairPathDatabase.getInstance(context)
                    repository = ContactRepository(database!!.contactDao())
                    signatureRepo = SignatureRepository(database!!.signatureDao())
                }
            }
        }
    }

    fun getRepository(): ContactRepository {
        return repository ?: throw IllegalStateException(
            "DatabaseProvider not initialized. Call DatabaseProvider.initialize(context) in your Application.onCreate()"
        )
    }

    fun getSignatureRepository(): SignatureRepository {
        return signatureRepo ?: throw IllegalStateException(
            "DatabaseProvider not initialized. Call DatabaseProvider.initialize(context) in your Application.onCreate()"
        )
    }

    fun getDatabase(): FairPathDatabase {
        return database ?: throw IllegalStateException(
            "DatabaseProvider not initialized. Call DatabaseProvider.initialize(context) in your Application.onCreate()"
        )
    }
}