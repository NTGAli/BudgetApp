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


fun String.countryCodeToUnicodeFlag(): String {
  return this
    .filter { it in 'A'..'Z' }
    .map { it.toByte() }
    .flatMap { char ->
      listOf(
        // First UTF-16 char is \uD83C
        0xD8.toByte(),
        0x3C.toByte(),
        // Second char is \uDDE6 for A and increments from there
        0xDD.toByte(),
        (0xE6.toByte() + (char - 'A'.toByte())).toByte()
      )
    }
    .toByteArray()
    .let { bytes ->
      String(bytes, Charsets.UTF_16)
    }
}


fun getTimeStamp(year: Int, month: Int, day: Int, hour: Int, minute: Int): Long {
  val calendar = Calendar.getInstance()
  calendar.set(year, month - 1, day, hour, minute, 0)
  return calendar.timeInMillis
}
