package com.oka.room.jooka.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oka.room.jooka.com.Files56ji5.animate
import com.oka.room.jooka.com.Files56ji5.click
import com.oka.room.jooka.com.databinding.ActivityMainBinding

class MainActivity56ji5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).run {
            setContentView(root)
            getSharedPreference().getLastPage()?.let { startWebViewActivity() }
            setupFireBase56ji5()
            click {
                animate()
                getSharedPreference().setUpLink()
                delayedIntentPush()
            }
        }
    }
}