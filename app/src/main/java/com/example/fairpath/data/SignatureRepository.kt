package com.example.fairpath.data

import com.example.fairpath.data.db.SignatureDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SignatureRepository(private val dao: SignatureDao) {
    val signature: Flow<Signature> = dao.observe().map { it ?: Signature() }

    suspend fun update(name: String, title: String, email: String, phone: String) {
        dao.upsert(
            Signature(
                id = Signature.SINGLETON_ID,
                name = name.trim(),
                title = title.trim(),
                email = email.trim(),
                phone = phone.trim()
            )
        )
    }
}