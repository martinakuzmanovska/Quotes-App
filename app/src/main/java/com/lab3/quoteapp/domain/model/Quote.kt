package com.lab3.quoteapp.domain.model

data class Quote (
    val id: Long,
    val quote: String,
    val author: String
)
data class QuotesResponse(
    val quotes: List<Quote>
)