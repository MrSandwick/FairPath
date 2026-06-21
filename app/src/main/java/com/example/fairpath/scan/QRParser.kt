package com.example.fairpath.scan

object QRParser {
    fun parse(raw: String): Map<String, String> {
        val result = mutableMapOf<String, String>()

        // Try MECARD format (common in business cards)
        if (raw.startsWith("MECARD:")) {
            parseMecard(raw, result)
        }
        // Try mailto: URI
        else if (raw.startsWith("mailto:")) {
            val email = raw.removePrefix("mailto:").takeWhile { it != '?' && it != '&' }
            result["email"] = email
        }
        // Try vCard format (BEGIN:VCARD...END:VCARD)
        else if (raw.contains("BEGIN:VCARD")) {
            parseVcard(raw, result)
        }
        // Plain text: try to extract name and email
        else {
            parsePlainText(raw, result)
        }

        return result
    }

    private fun parseMecard(raw: String, result: MutableMap<String, String>) {
        val content = raw.removePrefix("MECARD:").removeSuffix(";")
        val pairs = content.split(";")

        pairs.forEach { pair ->
            val (key, value) = pair.split(":", limit = 2).let { parts ->
                if (parts.size == 2) parts[0] to parts[1] else return@forEach
            }

            when (key.uppercase()) {
                "N" -> {
                    val nameParts = value.split(",")
                    result["name"] = when {
                        nameParts.size >= 2 -> "${nameParts[1]} ${nameParts[0]}".trim()
                        else -> value
                    }
                }
                "TEL" -> result["phone"] = value
                "EMAIL" -> result["email"] = value
                "ORG" -> result["company"] = value
                "TITLE" -> result["role"] = value
            }
        }
    }

    private fun parseVcard(raw: String, result: MutableMap<String, String>) {
        val lines = raw.split("\n").filter { it.isNotBlank() }

        lines.forEach { line ->
            when {
                line.startsWith("FN:") -> result["name"] = line.removePrefix("FN:").trim()
                line.startsWith("N:") -> {
                    val nameParts = line.removePrefix("N:").split(";")
                    if (nameParts.size >= 2) {
                        result["name"] = "${nameParts[1]} ${nameParts[0]}".trim()
                    }
                }
                line.startsWith("EMAIL") -> {
                    val email = line.substringAfter(":").trim()
                    if (email.isNotEmpty()) result["email"] = email
                }
                line.startsWith("TEL") -> {
                    val phone = line.substringAfter(":").trim()
                    if (phone.isNotEmpty()) result["phone"] = phone
                }
                line.startsWith("ORG:") -> result["company"] = line.removePrefix("ORG:").trim()
                line.startsWith("TITLE:") -> result["role"] = line.removePrefix("TITLE:").trim()
            }
        }
    }

    private fun parsePlainText(raw: String, result: MutableMap<String, String>) {
        val lines = raw.trim().split("\n").map { it.trim() }.filter { it.isNotEmpty() }

        lines.forEachIndexed { index, line ->
            when {
                // Check if line looks like an email
                "@" in line && "." in line -> result["email"] = line
                // Check if line looks like a phone (contains mostly digits and common separators)
                line.all { it.isDigit() || it in "()-+./ " } && line.any { it.isDigit() } -> {
                    result["phone"] = line
                }
                // First non-email, non-phone line is likely the name
                index == 0 || (result["name"].isNullOrEmpty() && "@" !in line) -> {
                    if (result["name"].isNullOrEmpty()) result["name"] = line
                }
            }
        }
    }
}
