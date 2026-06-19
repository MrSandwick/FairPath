package com.example.fairpath.data.db

import androidx.room.TypeConverter

class ContactTypeConverters {
    @TypeConverter
    fun tagsToString(tags: List<String>): String = tags.joinToString("|")

    @TypeConverter
    fun stringToTags(raw: String): List<String> =
        if (raw.isEmpty()) emptyList() else raw.split("|")
}
