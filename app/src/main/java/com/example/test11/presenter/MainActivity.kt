package com.example.test11.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test11.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}