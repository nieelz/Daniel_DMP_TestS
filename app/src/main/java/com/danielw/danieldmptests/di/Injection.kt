package com.danielw.danieldmptests.di

import android.content.Context
import com.danielw.danieldmptests.data.QuoteRepository
import com.danielw.danieldmptests.database.QuoteDatabase
import com.danielw.danieldmptests.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): QuoteRepository {
        val database = QuoteDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return QuoteRepository(database, apiService)
    }
}