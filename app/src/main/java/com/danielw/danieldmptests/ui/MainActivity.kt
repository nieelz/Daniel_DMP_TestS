package com.danielw.danieldmptests.ui

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielw.danieldmptests.adapter.JobListAdapter
import com.danielw.danieldmptests.adapter.LoadingStateAdapter
import com.danielw.danieldmptests.databinding.ActivityMainBinding
import com.danielw.danieldmptests.network.JobResponseItem
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvQuote.layoutManager = LinearLayoutManager(this)


//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//            android.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                mainViewModel.setUserData(query)
//                return false
//            }

        getData()


    }

    private fun getData() {
        val adapter = JobListAdapter(object : JobListAdapter.OnItemClickListener{
            override fun onItemClick(jobResponseItem: JobResponseItem) {
                val jobResponseItemJson = Gson().toJson(jobResponseItem)
                val intent = Intent(this@MainActivity, JobDetailActivity::class.java)
                intent.putExtra(JobDetailActivity.USER_ID, jobResponseItemJson)
                startActivity(intent)
            }
        })
        binding.rvQuote.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        mainViewModel.quote.observe(this, {
            adapter.submitData(lifecycle, it)
        })
    }
}