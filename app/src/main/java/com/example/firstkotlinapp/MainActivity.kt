package com.example.firstkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.firstkotlinapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 상수
    companion object {
        // const val은 컴파일 시간에 결정
        // val은 런타임에 결정
        private const val LOG_TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        button.setOnClickListener {
            Log.d(LOG_TAG, user_name.text.toString())
            Log.d(LOG_TAG, user_password.text.toString())
        }
    }
}