package com.example.firstkotlinapp

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_UP
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.example.firstkotlinapp.databinding.SearchBarBinding
import java.text.SimpleDateFormat
import java.util.*

class SearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var LOG_TAG = "SearchBar"

    private var mBinding: SearchBarBinding =
        SearchBarBinding.inflate(LayoutInflater.from(context), this, true)

    private val mSearchEngineList: Array<String> = arrayOf("Google", "Naver")
    private lateinit var mSearchEngine: String
    private var mAdapter: ArrayAdapter<String>


    init {
        mAdapter =
            ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, mSearchEngineList)
        mBinding.spinner.adapter = mAdapter
        mBinding.spinner.setSelection(0)

        mBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mSearchEngine = mAdapter.getItem(position) ?: "default"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        mBinding.searchButton.setOnClickListener { onSearch() }

        mBinding.keyword.setOnKeyListener { view: View, keyCode: Int, keyEvent: KeyEvent ->
            if (keyCode == KEYCODE_ENTER && keyEvent.action == ACTION_UP) {
                onSearch()
                true
            } else
                false
        }
    }

    private fun onSearch() {
        Toast.makeText(context, mBinding.keyword.text.toString(), Toast.LENGTH_SHORT)
            .show()
        if (context is MainActivity) {
            val sdf = SimpleDateFormat("yyyy/M/dd/ hh:mm:ss")
            val currentDate = sdf.format(Date())
            Log.d(LOG_TAG, currentDate)

            val searchHistory =
                SearchHistory(mSearchEngine, mBinding.keyword.text.toString(), currentDate)

            val activity: MainActivity = context as MainActivity
            activity.addHistoryItem(searchHistory)
        }
    }
}