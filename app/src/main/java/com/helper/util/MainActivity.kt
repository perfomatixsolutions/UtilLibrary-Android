package com.helper.util

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.helper.*
import com.helper.utillibrary.BasicUtil
import com.helper.utillibrary.DateUtil


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val basicUtil = BasicUtil()
        val dateUtil = DateUtil()

        basicUtil.showToastShort(this, dateUtil.dateTimeFormatTo12Hr("15:24").toString())
    }
}