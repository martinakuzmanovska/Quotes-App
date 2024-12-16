package com.lab3.quoteapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.lab3.quoteapp.R
import com.lab3.quoteapp.databinding.FragmentQuoteDetailsBinding
import com.lab3.quoteapp.databinding.FragmentQuoteListBinding
import com.lab3.quoteapp.viewmodel.SingleQuoteViewModel

class QuoteDetailsFragment : Fragment(R.layout.fragment_quote_details) {

    private val singleQuoteViewModel: SingleQuoteViewModel by activityViewModels()
    private var _binding: FragmentQuoteDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding =  FragmentQuoteDetailsBinding.bind(view)

        singleQuoteViewModel.quote.observe(viewLifecycleOwner) {
            binding.quoteId.text = it.id.toString()
            binding.quoteText.text = it.quote
            binding.author.text = it.author
        }
    }
}