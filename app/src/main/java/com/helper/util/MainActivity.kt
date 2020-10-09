package com.helper.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.helper.*
import com.helper.utillibrary.BasicUtil


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val basicUtil = BasicUtil()
        basicUtil.showToastShort(this, basicUtil.getDataConnectionType(this).toString())
    }
}