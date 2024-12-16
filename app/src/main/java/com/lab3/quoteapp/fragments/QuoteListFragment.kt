package com.lab3.quoteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.lab3.quoteapp.R
import com.lab3.quoteapp.adapters.QuoteAdapter
import com.lab3.quoteapp.domain.model.Quote
import com.lab3.quoteapp.databinding.FragmentQuoteListBinding
import com.lab3.quoteapp.viewmodel.QuoteViewModel
import com.lab3.quoteapp.viewmodel.QuotesViewModelFactory
import com.lab3.quoteapp.viewmodel.SingleQuoteViewModel

class QuoteListFragment : Fragment(R.layout.fragment_quote_list) {

    private lateinit var listView: RecyclerView
    private lateinit var viewModel: QuoteViewModel
    private val singleQuoteViewModel: SingleQuoteViewModel by activityViewModels()

    private var _binding: FragmentQuoteListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = QuotesViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[QuoteViewModel::class.java]


        _binding = FragmentQuoteListBinding.bind(view)

        viewModel.setQuotes()

        var adapter: QuoteAdapter = QuoteAdapter() {}
        binding.listView.adapter = adapter

        viewModel.allQuotes.observe(viewLifecycleOwner) {
            adapter.updateQuotes(it)
            adapter.updateFunction { selectedQuote ->
                singleQuoteViewModel.setQuote(selectedQuote)
                parentFragmentManager.commit {
                    replace(R.id.fragment_container_view, QuoteDetailsFragment())
                    setReorderingAllowed(true)
                    addToBackStack(null)
                }
            }

        }

    }}