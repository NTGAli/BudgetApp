package com.ntg.core.data.util

import java.security.MessageDigest

fun generateUniqueFiveDigitId(): Int {
  val timestamp = System.currentTimeMillis()
  val input = "$timestamp".toByteArray()
  val digest = MessageDigest.getInstance("SHA-256").digest(input)
  val hash = digest.fold(0) { acc, byte -> (acc shl 8) + byte.toInt() }
  return hash and 0x7FFFFFFF % 90000 + 10000 // Ensures 5-digit ID
}
