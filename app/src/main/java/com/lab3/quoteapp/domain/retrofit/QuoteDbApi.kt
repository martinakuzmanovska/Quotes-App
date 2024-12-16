package com.lab3.quoteapp.domain.retrofit

import com.lab3.quoteapp.domain.model.Quote
import com.lab3.quoteapp.domain.model.QuotesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteDbApi {

    @GET("quotes")
    suspend fun getQuotes(): Response<QuotesResponse>
}