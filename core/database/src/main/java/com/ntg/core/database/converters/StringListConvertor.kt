package com.ntg.core.database.converters

import androidx.room.TypeConverter

class StringListConvertor {

    @TypeConverter
    fun toString(value: List<String>?): String? {
        return value?.joinToString(separator = ",") { it }
    }

    @TypeConverter
    fun toList(date: String?): List<String>? {
        return date?.split(",")?.map { it }
    }
}
