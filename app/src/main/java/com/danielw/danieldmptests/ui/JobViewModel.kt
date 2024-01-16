package com.danielw.danieldmptests.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danielw.danieldmptests.network.JobResponseItem

class JobViewModel : ViewModel() {

    private val _detailAccount = MutableLiveData<JobResponseItem>()

    fun detailAccount(): LiveData<JobResponseItem> {
        return _detailAccount
    }

    fun setJob(jobResponseItem: JobResponseItem){
        _detailAccount.value = jobResponseItem
    }


}