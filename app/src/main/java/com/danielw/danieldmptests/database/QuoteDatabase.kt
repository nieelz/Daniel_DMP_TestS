package com.danielw.danieldmptests.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danielw.danieldmptests.network.JobResponse

@Database(entities = [JobResponse::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class QuoteDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): QuoteDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDatabase::class.java, "job_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}