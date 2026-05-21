package com.example.fairpath.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object SignatureRepository {
    var signature by mutableStateOf(Signature())
        private set

    fun update(name: String, title: String, email: String, phone: String) {
        signature = Signature(
            name = name.trim(),
            title = title.trim(),
            email = email.trim(),
            phone = phone.trim()
        )
    }
}
