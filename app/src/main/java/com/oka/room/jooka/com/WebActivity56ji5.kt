package com.oka.room.jooka.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oka.room.jooka.com.Files56ji5.checkIfLast
import com.oka.room.jooka.com.Files56ji5.loadCurrent
import com.oka.room.jooka.com.Files56ji5.refresh
import com.oka.room.jooka.com.Files56ji5.setUpWebView
import com.oka.room.jooka.com.databinding.ActivityWebActivity56ji5Binding

class WebActivity56ji5 : AppCompatActivity(), StartActivityForResult56ji5 {

    private lateinit var binding56ji5: ActivityWebActivity56ji5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding56ji5 = ActivityWebActivity56ji5Binding.inflate(layoutInflater).apply {
            setContentView(root)
            setUpWebView(this@WebActivity56ji5)
            refresh { loadCurrent() }
        }
    }

    override fun activityForResultPush(intent: Intent) {
        startActivityForResult(intent, REQUEST_CODE_56ji5)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        filePathCallback56ji5?.run {
            returnFileToWebView(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() = if (binding56ji5.checkIfLast()) binding56ji5.wv56ji5.goBack() else super.onBackPressed()
}