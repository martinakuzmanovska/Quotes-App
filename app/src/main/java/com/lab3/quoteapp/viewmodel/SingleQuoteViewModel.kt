package com.lab3.quoteapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lab3.quoteapp.domain.model.Quote

class SingleQuoteViewModel: ViewModel() {
    private val mutableQuote: MutableLiveData<Quote> by lazy {
        MutableLiveData<Quote>()
    }

    val quote: LiveData<Quote> get() = mutableQuote

    fun setQuote(quote:Quote) {
        this.mutableQuote.value = quote
    }
}