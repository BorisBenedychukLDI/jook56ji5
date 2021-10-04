package com.oka.room.jooka.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oka.room.jooka.com.Files56ji5.checkIfLast56ji5
import com.oka.room.jooka.com.Files56ji5.loadCurrent56ji5
import com.oka.room.jooka.com.Files56ji5.refresh56ji5
import com.oka.room.jooka.com.Files56ji5.setUpWebView56ji5
import com.oka.room.jooka.com.databinding.ActivityWebActivity56ji5Binding
import kotlinx.coroutines.*

class WebActivity56ji5 : AppCompatActivity(), StartActivityForResult56ji5 {

    private lateinit var binding56ji5: ActivityWebActivity56ji5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding56ji5 = ActivityWebActivity56ji5Binding.inflate(layoutInflater).apply {
            setContentView(root)
            setUpWebView56ji5(this@WebActivity56ji5)
            refresh56ji5 { loadCurrent56ji5() }
        }
        checkConnectionCycle56ji5()
    }

    override fun activityForResultPush56ji5(intent56ji5: Intent) {
        startActivityForResult(intent56ji5, REQUEST_CODE_56ji5)
    }

    override fun onActivityResult(requestCode56ji5: Int, resultCode56ji5: Int, data56ji5: Intent?) {
        filePathCallback56ji5?.run {
            returnFileToWebView(requestCode56ji5, resultCode56ji5, data56ji5)
        }
        super.onActivityResult(requestCode56ji5, resultCode56ji5, data56ji5)
    }

    override fun onBackPressed() =
        if (binding56ji5.checkIfLast56ji5()) binding56ji5.wv56ji5.goBack() else super.onBackPressed()


}


