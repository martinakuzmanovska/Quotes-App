package com.lab3.quoteapp.domain.repository

import com.lab3.quoteapp.domain.RemoteQuoteDataSource
import com.lab3.quoteapp.domain.model.Quote

class QuoteRepository(private val remoteQuoteDataSource: RemoteQuoteDataSource) {

    suspend fun getQuotes(): List<Quote> {
        return remoteQuoteDataSource.getQuotes()
    }
}