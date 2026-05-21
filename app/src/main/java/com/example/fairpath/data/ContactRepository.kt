package com.example.fairpath.data

import androidx.compose.runtime.mutableStateListOf

object ContactRepository {
    val contacts = mutableStateListOf<Contact>()

    fun add(contact: Contact) { contacts.add(contact) }

    fun remove(id: String) { contacts.removeAll { it.id == id } }

    fun getById(id: String): Contact? = contacts.find { it.id == id }

    fun updateNote(id: String, note: String) {
        val index = contacts.indexOfFirst { it.id == id }
        if (index != -1) contacts[index] = contacts[index].copy(note = note)
    }
}
