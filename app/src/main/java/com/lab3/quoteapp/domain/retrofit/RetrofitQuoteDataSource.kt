package com.lab3.quoteapp.domain.retrofit

import com.lab3.quoteapp.domain.RemoteQuoteDataSource
import com.lab3.quoteapp.domain.model.Quote

class RetrofitQuoteDataSource(private val quoteDbApi: QuoteDbApi): RemoteQuoteDataSource {
    override suspend fun getQuotes(): List<Quote> {
        val quoteResponse = quoteDbApi.getQuotes()
        val responseBody = quoteResponse.body()
        if (quoteResponse.isSuccessful && responseBody != null){
            return responseBody.quotes
        }
       // return emptyList()
      throw Exception(quoteResponse.message())
    }
}