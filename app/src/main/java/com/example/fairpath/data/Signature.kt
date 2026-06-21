package com.example.fairpath.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "signature")
data class Signature(
    @PrimaryKey val id: Int = SINGLETON_ID,
    val name: String = "",
    val title: String = "",
    val email: String = "",
    val phone: String = ""
) {
    companion object {
        const val SINGLETON_ID: Int = 0
    }

    val hasContent: Boolean
        get() = name.isNotBlank() || title.isNotBlank() || email.isNotBlank() || phone.isNotBlank()

    val formatted: String
        get() = listOfNotNull(
            name.takeIf { it.isNotBlank() },
            title.takeIf { it.isNotBlank() },
            email.takeIf { it.isNotBlank() },
            phone.takeIf { it.isNotBlank() }
        ).joinToString("\n")
}
