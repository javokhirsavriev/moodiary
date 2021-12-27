package uz.javokhirdev.moodiary.data.db.days

import javax.inject.Inject

class DaysRepository @Inject constructor(private val daysDao: DaysDao) {

    suspend fun getDay(createdAt: String): DayEntity? = daysDao.getDay(createdAt)

    suspend fun getDays(): List<DayEntity> = daysDao.getDays()

    suspend fun insert(obj: DayEntity): Long = daysDao.insert(obj)

    suspend fun delete(obj: DayEntity) = daysDao.delete(obj)

    suspend fun deleteAll() = daysDao.deleteAll()
}
