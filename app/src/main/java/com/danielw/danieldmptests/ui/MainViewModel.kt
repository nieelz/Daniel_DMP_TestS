package com.danielw.danieldmptests.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.danielw.danieldmptests.data.QuoteRepository
import com.danielw.danieldmptests.di.Injection
import com.danielw.danieldmptests.network.JobResponse
import com.danielw.danieldmptests.network.JobResponseItem


class MainViewModel(quoteRepository: QuoteRepository) : ViewModel() {

    val quote: LiveData<PagingData<JobResponseItem>> =
         quoteRepository.getQuote().cachedIn(viewModelScope)


}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }







}