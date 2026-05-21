package com.example.fairpath.data

import java.util.UUID

data class Contact(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val role: String = "",
    val company: String = "",
    val email: String = "",
    val phone: String = "",
    val note: String = "",
    val tags: List<String> = emptyList()
)
