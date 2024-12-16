package com.lab3.quoteapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lab3.quoteapp.R
import com.lab3.quoteapp.domain.model.Quote

class QuoteAdapter(val data: ArrayList<Quote> = ArrayList<Quote>(), private var onItemClicked: (Quote) -> Unit): RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>(){

    class QuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var quoteId: TextView
        private var quote: TextView
        private var author: TextView
        private var quoteObject: Quote?

        init {
            quoteId = itemView.findViewById(R.id.quoteId)
            quote = itemView.findViewById(R.id.quoteText)
            author = itemView.findViewById(R.id.author)
            quoteObject = null
        }

        fun bind(quote: Quote?, onClick: (Quote) -> Unit){
            this.quoteObject = quote
            this.quoteId.text = quote?.id.toString()
            this.quote.text = quote?.quote
            this.author.text = quote?.author

            itemView.setOnClickListener {
                if (quote != null) {
                    onClick(quote)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_list_item, parent, false)
        return QuoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(data?.get(position), onItemClicked)
    }

    fun updateQuotes(quotes: List<Quote>){
        data.clear()
        data.addAll(quotes)
        notifyDataSetChanged()
    }

    fun updateFunction(onItemClicked: (Quote) -> Unit) {
        this.onItemClicked = onItemClicked
    }
}