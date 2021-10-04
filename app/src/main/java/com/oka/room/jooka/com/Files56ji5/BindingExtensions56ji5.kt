package com.oka.room.jooka.com.Files56ji5

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.webkit.*
import androidx.core.animation.doOnEnd
import androidx.core.content.FileProvider
import com.oka.room.jooka.com.*
import com.oka.room.jooka.com.databinding.ActivityMain56ji5Binding
import com.oka.room.jooka.com.databinding.ActivityWebActivity56ji5Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

fun ActivityMain56ji5Binding.animate56ji5() {
    CoroutineScope(Dispatchers.Main).launch {
        launch {
            while (pbMa56ji5.progress < pbMa56ji5.max) {
                pbMa56ji5.progress += 1
                delay(50)
            }
        }
        ivLogo56ji5.animate().alpha(1f).run {
            startDelay = 1500
            duration = 1500
        }
        ObjectAnimator.ofFloat(ivBf56ji5, View.ROTATION, -30f).run {
            startDelay = 500
            duration = 125
            doOnEnd {
                ObjectAnimator.ofFloat(ivBf56ji5, View.ROTATION, -30f, 30f).run {
                    duration = 250
                    repeatCount = 3
                    repeatMode = ValueAnimator.REVERSE
                    doOnEnd {
                        ivBf56ji5.animate().rotation(0f)
                    }
                    start()
                }
            }
            start()
        }
    }
}


fun ActivityMain56ji5Binding.click56ji5(buttonWillTakeIt56ji5: () -> Unit) {
    bStart56ji5.setOnClickListener {
        buttonWillTakeIt56ji5()
    }
}

fun ActivityWebActivity56ji5Binding.refresh56ji5(refresh56ji5: () -> Unit) {
    root.setOnRefreshListener {
        refresh56ji5()
    }
}

fun ActivityWebActivity56ji5Binding.loadCurrent56ji5() {
    wv56ji5.loadUrl(wv56ji5.url ?: return)
    root.isRefreshing = false
}


fun ActivityWebActivity56ji5Binding.setUpWebView56ji5(start56ji5: StartActivityForResult56ji5) {
    wv56ji5.run {
        scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        settings.run {
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            loadWithOverviewMode = true
            builtInZoomControls = true
            domStorageEnabled = true
            displayZoomControls = false
            mediaPlaybackRequiresUserGesture = false
            javaScriptEnabled = true
            loadsImagesAutomatically = true
            useWideViewPort = true
            displayZoomControls = false
        }
        webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view56ji5: WebView?,
                request56ji5: WebResourceRequest?
            ): Boolean {
                val url56ji5 = request56ji5?.url
                return when {
                    listOf("mailto:", "tel").filter { goToLink56ji5 ->
                        url56ji5.toString().contains(goToLink56ji5)
                    }.isNotEmpty() -> {
                        root.context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                url56ji5
                            )
                        )
                        true
                    }
                    listOf(
                        "facebook",
                        "instagram",
                        "vk.com"
                    ).filter { prohib56ji5 -> url56ji5.toString().contains(prohib56ji5) }
                        .isNotEmpty() -> {
                        true
                    }
                    else -> false
                }
            }

            override fun onPageFinished(view56ji5: WebView?, url56ji5: String?) {
                view56ji5?.context?.getSharedPreference56ji5()?.putLastPage56ji5(url56ji5)
                super.onPageFinished(view56ji5, url56ji5)
            }
        }
        webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView56ji5: WebView?,
                filePathCallback56ji5: ValueCallback<Array<Uri>>?,
                fileChooserParams56ji5: FileChooserParams?
            ): Boolean {
                this@setUpWebView56ji5.root.context.run {
                    checkPermissions56ji5()
                    com.oka.room.jooka.com.filePathCallback56ji5 = filePathCallback56ji5
                    val capture56ji5 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    if (capture56ji5.resolveActivity(this.packageManager) != null) {
                        val file = createTempFile56ji5()
                        uriView56ji5 = FileProvider.getUriForFile(
                            this,
                            "${bundle56ji5}.provider",
                            file
                        )
                        capture56ji5.run {
                            putExtra(MediaStore.EXTRA_OUTPUT, uriView56ji5)
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                        val actionIntent56ji5 = Intent(Intent.ACTION_GET_CONTENT).apply {
                            addCategory(Intent.CATEGORY_OPENABLE)
                            type = "image/*"
                        }
                        val intentChooser56ji5 = Intent(Intent.ACTION_CHOOSER).apply {
                            putExtra(Intent.EXTRA_INTENT, capture56ji5)
                            putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(actionIntent56ji5))
                        }
                        start56ji5.activityForResultPush56ji5(intentChooser56ji5)
                        return true
                    }

                }
                return super.onShowFileChooser(webView56ji5, filePathCallback56ji5, fileChooserParams56ji5)
            }
        }
        this@setUpWebView56ji5.root.context.getSharedPreference56ji5().run {
            if (getLastPage56ji5() != null) {
                loadUrl(getLastPage56ji5()!!)
            } else {
                loadUrl(getFirstPage56ji5() ?: return)
            }
        }
    }
}

fun ActivityWebActivity56ji5Binding.checkIfLast56ji5(): Boolean =
    wv56ji5.canGoBack() && wv56ji5.isFocused