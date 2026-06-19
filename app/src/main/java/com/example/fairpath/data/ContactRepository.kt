package com.example.fairpath.data

import com.example.fairpath.data.db.ContactDao
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val dao: ContactDao) {
    val contacts: Flow<List<Contact>> = dao.getAll()

    suspend fun add(contact: Contact) = dao.insert(contact)

    suspend fun remove(contact: Contact) = dao.delete(contact)

    suspend fun getById(id: String): Contact? = dao.getById(id)

    suspend fun updateNote(id: String, note: String) {
        dao.getById(id)?.let {
            dao.update(it.copy(note = note, updatedAt = System.currentTimeMillis()))
        }
    }
}
