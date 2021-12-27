package uz.javokhirdev.moodiary.utils

import android.os.Build

/* Constants */
const val MATCH_PARENT = -1
const val WRAP_CONTENT = -2
const val DAYS_COUNT = 42
const val ROW_COUNT = 6
const val COLUMN_COUNT = 7
const val PREFILLED_MONTHS = 2400

/* Date and time formats */
const val DATE_FORMAT_1 = "yyyyMMdd"
const val DATE_FORMAT_2 = "EEEE, MMMM dd, yyyy"
const val DATE_FORMAT_3 = "yyyyMM"
const val DAY_PATTERN = "dd"
const val MONTH_PATTERN = "MMMM"
const val YEAR_PATTERN = "yyyy"

/* Build versions */
fun isMarshmallowPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isNougatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isNougatMRPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun isRPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
