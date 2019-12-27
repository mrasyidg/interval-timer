package id.example.intervaltimerapp.dao

import androidx.room.*
import id.example.intervaltimerapp.model.HistoryEntity

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