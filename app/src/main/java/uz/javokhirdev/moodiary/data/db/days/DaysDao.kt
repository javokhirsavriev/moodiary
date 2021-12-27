package uz.javokhirdev.moodiary.data.db.days

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaysDao {

    @Query("SELECT * FROM days WHERE createdAt = :createdAt")
    suspend fun getDay(createdAt: String): DayEntity?

    @Query("SELECT * FROM days")
    suspend fun getDays(): List<DayEntity>

    @Insert
    suspend fun insert(obj: DayEntity): Long

    @Delete
    suspend fun delete(obj: DayEntity)

    @Query("DELETE FROM days")
    suspend fun deleteAll()
}