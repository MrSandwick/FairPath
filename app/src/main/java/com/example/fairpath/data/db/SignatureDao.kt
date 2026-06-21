package com.example.fairpath.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fairpath.data.Signature
import kotlinx.coroutines.flow.Flow

@Dao
interface SignatureDao {
    @Query("SELECT * FROM signature WHERE id = ${Signature.SINGLETON_ID} LIMIT 1")
    fun observe(): Flow<Signature?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(signature: Signature)
}