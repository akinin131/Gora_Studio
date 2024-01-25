package com.example.gora_studio.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getThisWeekPeriod(): Pair<String, String> {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val startOfWeek = currentDate.with(java.time.DayOfWeek.MONDAY)

    val endOfWeek = startOfWeek.plusDays(-30)

    val startOfWeekString = startOfWeek.format(formatter)
    val endOfWeekString = endOfWeek.format(formatter)

    return Pair(startOfWeekString, endOfWeekString)
}
