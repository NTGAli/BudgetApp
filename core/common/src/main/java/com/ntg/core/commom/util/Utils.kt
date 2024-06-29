package com.ntg.core.commom.util

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("MMMM d yyyy", Locale.getDefault())
    return dateFormat.format(date)
}

fun getCurrentTime12Hour(): String {
    val calendar = Calendar.getInstance()
    val timeFormat = SimpleDateFormat("hh:mm a")
    return timeFormat.format(calendar.time)
}

fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val timeFormat = SimpleDateFormat("HH:mm")
    return timeFormat.format(calendar.time)
}

fun generateUniqueFiveDigitId(): Int {
    val timestamp = System.currentTimeMillis()
    val input = "$timestamp".toByteArray()
    val digest = MessageDigest.getInstance("SHA-256").digest(input)
    val hash = digest.fold(0) { acc, byte -> (acc shl 8) + byte.toInt() }
    return hash and 0x7FFFFFFF % 90000 + 10000 // Ensures 5-digit ID
}
