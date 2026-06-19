package com.example.fairpath.export

import android.content.Context
import androidx.core.content.FileProvider
import com.example.fairpath.data.Contact
import java.io.File
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter

object CsvExporter {
    fun generate(context: Context, contacts: List<Contact>): android.net.Uri {
        val dir = File(context.cacheDir, "exports").also { it.mkdirs() }
        val file = File(dir, "fairpath_contacts.csv")

        CSVPrinter(file.writer(), CSVFormat.DEFAULT.withHeader(
            "Name", "Role", "Company", "Email", "Phone", "Note", "Tags"
        )).use { printer ->
            contacts.forEach { c ->
                printer.printRecord(
                    c.name,
                    c.role,
                    c.company,
                    c.email ?: "",
                    c.phone,
                    c.note,
                    c.tags.joinToString("|")
                )
            }
        }

        return FileProvider.getUriForFile(
            context,
            "com.example.fairpath.fileprovider",
            file
        )
    }
}