package com.danielw.danieldmptests.database

import androidx.room.TypeConverter
import com.danielw.danieldmptests.network.JobResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun convertSource(jobResponse: String): List<JobResponseItem> {
        return Gson().fromJson(jobResponse, object : TypeToken<List<JobResponseItem>>() {}.type)
    }

    @TypeConverter
    fun convertSource(jobResponse: List<JobResponseItem>): String? {
        return Gson().toJson(jobResponse)
    }



}
