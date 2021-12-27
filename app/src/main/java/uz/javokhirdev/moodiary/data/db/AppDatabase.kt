package uz.javokhirdev.moodiary.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.javokhirdev.moodiary.data.db.days.DaysDao
import uz.javokhirdev.moodiary.data.db.days.DayEntity

@Database(entities = [DayEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daysDao(): DaysDao
}