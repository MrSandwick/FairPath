package com.example.fairpath.data

data class Signature(
    val name: String = "",
    val title: String = "",
    val email: String = "",
    val phone: String = ""
) {
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
