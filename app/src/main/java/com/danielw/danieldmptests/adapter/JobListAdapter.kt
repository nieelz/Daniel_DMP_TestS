package com.danielw.danieldmptests.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.danielw.danieldmptests.databinding.ItemQuoteBinding
import com.danielw.danieldmptests.network.JobResponse
import com.danielw.danieldmptests.network.JobResponseItem


class JobListAdapter (private val listener: OnItemClickListener) : PagingDataAdapter<JobResponseItem, JobListAdapter.MyViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    interface OnItemClickListener {
        fun onItemClick(jobResponseItem: JobResponseItem)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data, listener)
        }
    }

    class MyViewHolder(private val binding: ItemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: JobResponseItem, listener:OnItemClickListener) {
            Glide.with(binding.imageView).load(data.companyLogo).into(binding.imageView)
            binding.tvItemQuote.text = data.title
            binding.tvItemAuthor.text = data.company
            binding.tvItemLocation.text = data.location


            itemView.setOnClickListener{
                listener.onItemClick(data)
            }


        }





    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<JobResponseItem>() {
            override fun areItemsTheSame(oldItem: JobResponseItem, newItem: JobResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: JobResponseItem, newItem: JobResponseItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}