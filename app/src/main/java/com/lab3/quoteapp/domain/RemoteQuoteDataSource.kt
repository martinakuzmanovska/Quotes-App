package com.lab3.quoteapp.domain

import com.lab3.quoteapp.domain.model.Quote

interface RemoteQuoteDataSource {
    suspend fun getQuotes(): List<Quote>
}