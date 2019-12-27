package id.example.intervaltimerapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_content")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "history") var history: String,
    @ColumnInfo(name = "sets") var sets: Int
)