package id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.dao

import androidx.room.*
import id.ac.ui.cs.mobileprogramming.muhammad_rasyid.intervaltimerapp.model.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_content")
    fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM history_content WHERE date LIKE :date")
    fun findByTitle(date: String)

    @Insert
    fun insertAll(vararg date: HistoryEntity)

    @Delete
    fun delete(todo: HistoryEntity)

    @Update
    fun updateTodo(vararg dates: HistoryEntity)
}