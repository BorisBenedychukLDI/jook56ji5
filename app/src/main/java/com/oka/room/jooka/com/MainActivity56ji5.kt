package com.oka.room.jooka.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oka.room.jooka.com.Files56ji5.animate56ji5
import com.oka.room.jooka.com.Files56ji5.click56ji5
import com.oka.room.jooka.com.databinding.ActivityMain56ji5Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity56ji5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMain56ji5Binding.inflate(layoutInflater).run {
            setContentView(root)
            getSharedPreference56ji5().getLastPage56ji5()?.let { startWebViewActivity56ji5() }
            setupFireBase56ji5()
            click56ji5 {
                CoroutineScope(Dispatchers.Main).launch {
                    animate56ji5()
                    delay(5000)
                    getSharedPreference56ji5().setUpLink56ji5()
                    intentPush56ji5()
                }
            }
        }
    }
}