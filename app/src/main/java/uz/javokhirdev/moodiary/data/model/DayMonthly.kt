package uz.javokhirdev.moodiary.data.model

data class DayMonthly(
    val value: Int,
    val isThisMonth: Boolean,
    val code: String,
    val date: String
)