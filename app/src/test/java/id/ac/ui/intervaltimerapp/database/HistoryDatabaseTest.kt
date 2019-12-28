package id.ac.ui.intervaltimerapp.database

import androidx.room.Room
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.dao.HistoryDao
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.database.AppDatabase
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import org.junit.runners.JUnit4
import androidx.test.platform.app.InstrumentationRegistry
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.model.HistoryEntity

@RunWith(JUnit4::class)
class EntityReadWriteTest {
    private lateinit var historyDao: HistoryDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        historyDao = db.HistoryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

//    @Test
//    @Throws(Exception::class)
//    fun writeUserAndReadInList() {
//        val history: HistoryEntity = HistoryEntity(0,"2019", "5 minutes", 5)
//        historyDao.insertAll()
//        val historyItem = historyDao.findByDate(history.date)
//        assertThat(historyItem, equalTo(history))
//    }
}