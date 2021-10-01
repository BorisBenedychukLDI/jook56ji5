package com.oka.room.jooka.com.Files56ji5

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.core.content.FileProvider
import com.oka.room.jooka.com.*
import com.oka.room.jooka.com.databinding.ActivityMainBinding
import com.oka.room.jooka.com.databinding.ActivityWebActivity56ji5Binding
import java.util.*

fun ActivityMainBinding.animate() {

}

fun ActivityMainBinding.click(buttonWillTakeIt: () -> Unit) {
    button.setOnClickListener {
        buttonWillTakeIt()
    }
}

fun ActivityMainBinding.buttonShrinkAndLoad() {
    button.animate().scaleX(0.5f)
}

fun ActivityWebActivity56ji5Binding.refresh (refresh: () -> Unit) {
    root.setOnRefreshListener {
        refresh()
    }
}

fun ActivityWebActivity56ji5Binding.loadCurrent ()  {
    wv56ji5.loadUrl(wv56ji5.url ?: return)
    root.isRefreshing = false
}


fun ActivityWebActivity56ji5Binding.setUpWebView(start: StartActivityForResult56ji5) {
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
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url
                return when {
                    listOf("mailto:", "tel").filter { goToLink ->
                        url.toString().contains(goToLink)
                    }.isNotEmpty() -> {
                        root.context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                url
                            )
                        )
                        true
                    }
                    listOf(
                        "facebook",
                        "instagram",
                        "vk.com"
                    ).filter { prohibStuff -> url.toString().contains(prohibStuff) }
                        .isNotEmpty() -> {
                        true
                    }
                    else -> false
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                view?.context?.getSharedPreference()?.putLastPage(url)
                super.onPageFinished(view, url)
            }
        }
        webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                this@setUpWebView.root.context.run {
                    checkPermissions56ji5()
                    filePathCallback56ji5 = filePathCallback
                    val capture56ji5 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    if (capture56ji5.resolveActivity(this.packageManager) != null) {
                        val file = createTempFile56ji5()
                        uriView = FileProvider.getUriForFile(
                            this,
                            "${bundle56ji5}.provider",
                            file
                        )
                        capture56ji5.run {
                            putExtra(MediaStore.EXTRA_OUTPUT, uriView)
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
                        start.activityForResultPush(intentChooser56ji5)
                        return true
                    }

                }
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
            }
        }
        this@setUpWebView.root.context.getSharedPreference().run {
            if (getLastPage() != null) {
                loadUrl(getLastPage()!!)
            } else {
                loadUrl(getFirstPage() ?: return)
                Log.d("TEST_URL", getFirstPage() ?: return)
//                loadUrl("https://dropmefiles.com/")
            }
        }
    }
}

fun ActivityWebActivity56ji5Binding.checkIfLast (): Boolean = wv56ji5.canGoBack() || wv56ji5.isFocused