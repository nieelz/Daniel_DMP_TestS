package com.danielw.danieldmptests.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
   @GET("/api/recruitment/positions.json")
   suspend fun getQuote(
           @Query("page") page: Int
   ): List<JobResponseItem>
}