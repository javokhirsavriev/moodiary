package uz.javokhirdev.moodiary.utils

import android.os.Build

/* Constants */
const val MATCH_PARENT = -1
const val WRAP_CONTENT = -2

/* Date and time formats */
const val DATE_FORMAT_1 = "yyyyMMdd"
const val DATE_FORMAT_2 = "EEEE, MMMM dd, yyyy"

/* Build versions */
fun isMarshmallowPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isNougatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isNougatMRPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun isRPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
