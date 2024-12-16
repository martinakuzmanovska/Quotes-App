package com.lab3.quoteapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lab3.quoteapp.domain.model.Quote
import com.lab3.quoteapp.domain.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(private val quoteRepository: QuoteRepository): ViewModel() {

    private val _quotes: MutableLiveData<List<Quote>> by lazy {
        MutableLiveData<List<Quote>>()
    }
    private val mutableQuote: MutableLiveData<Quote> by lazy {
        MutableLiveData<Quote>()
    }

//    fun getQuotesValue(): List<Quote>{
//        return _quotes.value!!
//    }
//
//
//    val selectedQuote: LiveData<Quote> get() = mutableQuote
    val allQuotes: LiveData<List<Quote>>  get()= _quotes
//
//    fun setQuote(quote: Quote) {
//        this.mutableQuote.value = quote
//    }

    fun setQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val quotes = quoteRepository.getQuotes()
            _quotes.postValue(quotes)
        }
    }

}