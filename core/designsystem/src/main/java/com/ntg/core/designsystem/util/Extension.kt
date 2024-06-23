package com.ntg.designsystem.util

import android.util.Log
import com.ntg.budgetapp.core.designsystem.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.mask(mask: String): String {
    var maskedString = ""
    var valueIndex = 0
    var maskIndex = 0
    while (valueIndex < length && maskIndex < mask.length) {
        if (mask[maskIndex] == '#') {
            maskedString += get(valueIndex)
            valueIndex++
            maskIndex++
        } else {
            maskedString += mask[maskIndex]
            maskIndex++
        }
    }
    Log.d("MMMAAA", maskedString)
    return maskedString
}


fun detectCardType(cardNumber: String): Int {
    val cleanCardNumber = cardNumber.replace("\\D".toRegex(), "")
    return when {
        cleanCardNumber.startsWith("4") && (cleanCardNumber.length == 13 || cleanCardNumber.length == 16 || cleanCardNumber.length == 19) -> R.drawable.visa
        cleanCardNumber.startsWith("5") && cleanCardNumber.length == 16 -> R.drawable.master
        cleanCardNumber.matches("^3[47][0-9]{13}\$".toRegex()) -> R.drawable.amex
        cleanCardNumber.matches("^6(?:011|5[0-9]{2})[0-9]{12}\$".toRegex()) -> R.drawable.discover
        cleanCardNumber.matches("^3(?:0[0-5]|[68][0-9])[0-9]{11}\$".toRegex()) -> R.drawable.diners
        cleanCardNumber.matches("^35(2[89]|[3-8][0-9])[0-9]{12}\$".toRegex()) -> R.drawable.jcb
        else -> -1
    }
}

fun getTimeAndDate(timestamp: Long): Pair<String, String> {
    val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    val time = timeFormat.format(Date(timestamp))
    val date = dateFormat.format(Date(timestamp))

    return Pair(time, date)
}