package com.example.firstkotlinapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.example.firstkotlinapp.databinding.SearchBarBinding

class SearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mBinding : SearchBarBinding =
        SearchBarBinding.inflate(LayoutInflater.from(context), this, true)

    private val mSearchEngineList: Array<String> = arrayOf("Google", "Naver")
    private lateinit var mSearchEngine : String
    private var mAdapter : ArrayAdapter<String>


    init {
        mAdapter =
            ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, mSearchEngineList)
        mBinding.spinner.adapter = mAdapter
        mBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }
}