package com.lab3.quoteapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lab3.quoteapp.domain.repository.QuoteRepository
import com.lab3.quoteapp.domain.retrofit.QuoteDbApiProvider
import com.lab3.quoteapp.domain.retrofit.RetrofitQuoteDataSource

class QuotesViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(QuoteRepository::class.java)
            .newInstance(QuoteRepository(
                RetrofitQuoteDataSource(QuoteDbApiProvider.getQuoteDbApi())
            ))

    }

}