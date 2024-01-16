package com.danielw.danieldmptests.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danielw.danieldmptests.network.ApiService
import com.danielw.danieldmptests.network.JobResponse

import com.danielw.danieldmptests.network.JobResponseItem

class QuotePagingSource(private val apiService: ApiService) : PagingSource<Int, JobResponseItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobResponseItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getQuote(page)

            LoadResult.Page(
                data = responseData,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, JobResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}