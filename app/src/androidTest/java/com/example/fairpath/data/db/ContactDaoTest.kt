package com.example.fairpath.data.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.fairpath.data.Contact
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactDaoTest {
    private lateinit var database: FairPathDatabase
    private lateinit var dao: ContactDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, FairPathDatabase::class.java).build()
        dao = database.contactDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndRetrieve() = runBlocking {
        val contact = Contact(name = "Alice", email = "alice@test.com")
        dao.insert(contact)
        val retrieved = dao.getById(contact.id)
        assert(retrieved?.name == "Alice")
    }

    @Test
    fun deleteContact() = runBlocking {
        val contact = Contact(name = "Bob", email = "bob@test.com")
        dao.insert(contact)
        dao.delete(contact)
        val retrieved = dao.getById(contact.id)
        assert(retrieved == null)
    }

    @Test
    fun updateContact() = runBlocking {
        val contact = Contact(name = "Charlie", email = "charlie@test.com")
        dao.insert(contact)
        val updated = contact.copy(note = "Updated note")
        dao.update(updated)
        val retrieved = dao.getById(contact.id)
        assert(retrieved?.note == "Updated note")
    }

    @Test
    fun getAllEmitsOnInsert() = runBlocking {
        val initialList = dao.getAll().first()
        assert(initialList.isEmpty())

        val contact = Contact(name = "Diana", email = "diana@test.com")
        dao.insert(contact)

        val updatedList = dao.getAll().first()
        assert(updatedList.size == 1)
        assert(updatedList[0].name == "Diana")
    }

    @Test(expected = android.database.sqlite.SQLiteIntegrityConstraintException::class)
    fun uniqueEmailConstraint() = runBlocking {
        val contact1 = Contact(name = "Eve", email = "eve@test.com")
        val contact2 = Contact(name = "Frank", email = "eve@test.com")
        dao.insert(contact1)
        dao.insert(contact2)
    }
}
