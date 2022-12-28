package ru.itis.persikill.androidhomeworks.database;

import androidx.room.TypeConverter;

import java.util.*;
class DateConverters {
    @TypeConverter
    public static Date dateFromTimestamp(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

