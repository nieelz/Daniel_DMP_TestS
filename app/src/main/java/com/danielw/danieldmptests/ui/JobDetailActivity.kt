package com.danielw.danieldmptests.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
import com.danielw.danieldmptests.R
import com.danielw.danieldmptests.databinding.ActivityJobDetailBinding
import com.danielw.danieldmptests.network.JobResponse
import com.danielw.danieldmptests.network.JobResponseItem
import com.google.gson.Gson

class JobDetailActivity : AppCompatActivity() {

    private lateinit var jobDetailBinding: ActivityJobDetailBinding
    private val viewModel: JobViewModel by viewModels()

    private lateinit var currentUser: JobResponseItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jobDetailBinding = ActivityJobDetailBinding.inflate(layoutInflater)
        setContentView(jobDetailBinding.root)

        val id = intent.getStringExtra(USER_ID) as String

        val jobResponseItemJson = Gson().fromJson(id, JobResponseItem::class.java)

        viewModel.setJob(jobResponseItemJson)




        viewModel.detailAccount().observe(this) {
            showUserData(it)
        }

    }

    private fun showUserData(user: JobResponseItem) {
        currentUser = user

        jobDetailBinding.tvCompanyName.text = user.company
        jobDetailBinding.tvLocation.text = user.location
        jobDetailBinding.tvWebsite.text = user.companyUrl
        jobDetailBinding.tvJobTitle.text = user.title
        jobDetailBinding.tvyesno.text = user.type
        jobDetailBinding.tvJobDescription.text = HtmlCompat.fromHtml(user.description, HtmlCompat.FROM_HTML_MODE_LEGACY)



    }


    companion object {
        const val USER_ID = "nieelz"
            }

}