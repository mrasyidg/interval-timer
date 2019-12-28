package id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.dao.HistoryDao
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.model.HistoryEntity

@Database(
    entities = [HistoryEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase(){
    abstract fun HistoryDao(): HistoryDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "history.db")
            .build()
    }
}