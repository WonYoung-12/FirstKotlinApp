package com.example.firstkotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import com.example.firstkotlinapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 상수
    companion object {
        // const val은 컴파일 시간에 결정
        // val은 런타임에 결정
        private const val LOG_TAG = "MainActivity"
    }
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        createRecyclerView()
    }

    private fun createRecyclerView() {
        var historyList: ObservableArrayList<SearchHistory> = ObservableArrayList()
        historyList.add(SearchHistory("Google", "ddd", "2020-01-01"))
        historyList.add(SearchHistory("Naver", "ddd", "2020-01-01"))

        val adapter = SearchHistoryAdapter(historyList)
        binding.historyList.adapter = adapter
    }
}