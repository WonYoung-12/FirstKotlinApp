package com.example.firstkotlinapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import com.example.firstkotlinapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 상수
    companion object {
        // const val은 컴파일 시간에 결정
        // val은 런타임에 결정
        private const val TAG = "MainActivity"
    }

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mSearchHistoryAdapter: SearchHistoryAdapter
    private lateinit var mSearchHistoryList: ObservableArrayList<SearchHistory>
    private lateinit var mSearchHistoryDBHelper: SearchHistoryDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mSearchHistoryDBHelper = SearchHistoryDBHelper(this)
        createRecyclerView()
    }

    private fun createRecyclerView() {
        mSearchHistoryList = ObservableArrayList()

        initItems()
        mSearchHistoryAdapter = SearchHistoryAdapter(mSearchHistoryList)
        mSearchHistoryAdapter.setEventListener {
            object : EventListener {
                override fun onDeleteClick(searchHistory: SearchHistory) {
                    Log.v(TAG, "onDeleteClick")
                    mSearchHistoryDBHelper.deleteSearchHistory(searchHistory)
                }
            }
        }
        mBinding.recyclerView.adapter = mSearchHistoryAdapter
        mBinding.historyList = mSearchHistoryList
    }

    private fun initItems() {
        mSearchHistoryList.add(SearchHistory("Google", "ddd", "2020-01-01"))
        mSearchHistoryList.add(SearchHistory("Naver", "ddd", "2020-01-01"))
    }

    fun addHistoryItem(item: SearchHistory) {
        mSearchHistoryAdapter.addSearchHistory(item)
        mSearchHistoryDBHelper.insertSearchHistory(item)
    }
}