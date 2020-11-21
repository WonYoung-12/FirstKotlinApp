package com.example.firstkotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_history.view.*

class SearchHistoryAdapter(private var historyList : ObservableArrayList<SearchHistory>) :
    RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_history, parent, false)
        return SearchHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.apply {
            bind(item)
        }
    }

    override fun getItemCount() = historyList.size

    class SearchHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener{
                Toast.makeText(itemView.context, "Item Clicked", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(item: SearchHistory) {
            itemView.search_engine.text = item.searchEngine
            itemView.keyword.text = item.keyWord
        }
    }
}