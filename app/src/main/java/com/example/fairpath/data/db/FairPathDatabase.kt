package com.example.fairpath.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fairpath.data.Contact
import com.example.fairpath.data.Signature

@Database(entities = [Contact::class, Signature::class], version = 2, exportSchema = false)
@TypeConverters(ContactTypeConverters::class)
abstract class FairPathDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun signatureDao(): SignatureDao

    companion object {
        @Volatile
        private var INSTANCE: FairPathDatabase? = null

        fun getInstance(context: Context): FairPathDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    FairPathDatabase::class.java,
                    "fairpath.db"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                    .also { INSTANCE = it }
            }
    }
}
