package com.example.firstkotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinapp.databinding.SearchHistoryBinding


class SearchHistoryAdapter(private var historyList: ObservableArrayList<SearchHistory>) :
    RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    companion object {
        @BindingAdapter("item")
        @JvmStatic
        fun bindItem(recyclerView: RecyclerView, items: ObservableArrayList<SearchHistory>) {
            val adapter: SearchHistoryAdapter = recyclerView.adapter as SearchHistoryAdapter
            adapter.setItem(items)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val binding: SearchHistoryBinding =
            SearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.apply {
            bind(item, View.OnClickListener { deleteItem(adapterPosition) })
        }
    }

    override fun getItemCount() = historyList.size

    fun addSearchHistory(item: SearchHistory) {
        historyList.add(0, item)
    }

    private fun setItem(list: ObservableArrayList<SearchHistory>) {
        historyList = list
        notifyDataSetChanged()
    }

    private fun deleteItem(position: Int) {
        historyList.removeAt(position)
    }

    class SearchHistoryViewHolder(binding: SearchHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var mBinding: SearchHistoryBinding = binding

        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Item Clicked, position : " + adapterPosition, Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(item: SearchHistory, onClickListener: View.OnClickListener) {
            mBinding.searchHistory = item
            mBinding.executePendingBindings()
            mBinding.deleteButton.setOnClickListener(onClickListener)
        }
    }
}