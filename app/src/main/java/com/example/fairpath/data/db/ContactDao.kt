package com.example.fairpath.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fairpath.data.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY name ASC")
    fun getAll(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun getById(id: String): Contact?

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun observeById(id: String): Flow<Contact?>

    @Query("SELECT * FROM contacts WHERE name LIKE '%' || :q || '%' OR company LIKE '%' || :q || '%' OR email LIKE '%' || :q || '%'")
    fun search(q: String): Flow<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)
}
