package br.com.sanatiel.appfitness.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun toDate(dateInLong: Long?): Date? {
        return if(dateInLong != null) Date(dateInLong) else null
    }

    @TypeConverter
    fun fromDate(date: Date?): Long?{
        return date?.time
    }

}