package uz.javokhirdev.moodiary.data.db.days

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "days")
data class DayEntity(
    @PrimaryKey
    val createdAt: String,
    val dayOfMonth: String,
    val monthAndYear: String,
    val isGoodDay: Boolean = false
)