package com.danielw.danieldmptests.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.danielw.danieldmptests.database.QuoteDatabase
import com.danielw.danieldmptests.network.ApiService
import com.danielw.danieldmptests.network.JobResponse
import com.danielw.danieldmptests.network.JobResponseItem
import com.danielw.danieldmptests.network.QuoteResponseItem

class QuoteRepository(private val quoteDatabase: QuoteDatabase, private val apiService: ApiService) {
    fun getQuote(): LiveData<PagingData<JobResponseItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                QuotePagingSource(apiService)
            }
        ).liveData
    }
}