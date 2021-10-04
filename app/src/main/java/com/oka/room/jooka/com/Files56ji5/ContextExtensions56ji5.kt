package com.oka.room.jooka.com

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.onesignal.OneSignal
import kotlinx.coroutines.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


fun Context.setupOneSignal56ji5() {
    OneSignal.initWithContext(this)
    OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
    OneSignal.setAppId(ONE_SIGNAL_KEY_CODED_56JI5.decodeBase6456ji5())
}

fun Context.setUpMobileAds56ji5() {
    MobileAds.initialize(this)
    CoroutineScope(Dispatchers.IO).launch {
        try {
            gaid56ji5 =
                AdvertisingIdClient.getAdvertisingIdInfo(this@setUpMobileAds56ji5)?.id
        } catch (e: Exception) {

        }
    }
}

fun Context.setUpAppsFlyer56ji5() {
    object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(map56ji5: MutableMap<String, Any>?) {
            map56ji5?.run {
                status56ji5 = if (getValue(STATUS_TAG_56ji5) == "Organic") {
                    "Organic"
                } else {
                    "Non-organic"
                }

                val listOfSubs56ji5 = getValue(CAMPAIGN_TAG_56ji5)
                    .toString()
                    .split("||")
                    .drop(1)
                    .map { it.split(":")[1] }

                key56ji5 = listOfSubs56ji5[0]
                sub256ji5 = listOfSubs56ji5[1]
                sub356ji5 = listOfSubs56ji5[2]

                sub456ji5 = getValue(ADGROUP_TAG_56ji5).toString()
                sub556ji5 = getValue(AD_SET_TAG_56ji5).toString()
                sub656ji5 = getValue(AF_CHANNEL_TAG_56ji5).toString()
                sub756ji5 = getValue(MEDIA_SOURCE_TAG_56ji5).toString()
            }
        }

        override fun onConversionDataFail(p0: String?) {
        }

        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
        }

        override fun onAttributionFailure(p0: String?) {
        }
    }.let { afConvListener56ji5 ->
        AppsFlyerLib.getInstance().run {
            uid56ji5 = getAppsFlyerUID(this@setUpAppsFlyer56ji5)
            init(
                APPS_FLYER_KEY_CODED_56JI5.decodeBase6456ji5(),
                afConvListener56ji5,
                this@setUpAppsFlyer56ji5
            )
            start(this@setUpAppsFlyer56ji5)
        }
    }
}

fun Context.setupBundle56ji5() {
    bundle56ji5 = packageName
}


fun Context.checkPermissions56ji5() {
    Dexter.withContext(this)
        .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
            }
        }).check()
}

fun Context.getSharedPreference56ji5(): SharedPreferences =
    getSharedPreferences(SHARED_PREFERENCE_TAG_56ji5, Context.MODE_PRIVATE)

fun AppCompatActivity.startWebViewActivity56ji5() {
    startActivity(Intent(this, WebActivity56ji5::class.java))
    finish()
}

fun AppCompatActivity.intentPush56ji5() {
    startActivity(Intent(this, WebActivity56ji5::class.java))
    finish()
}

fun Context.createTempFile56ji5(): File = File.createTempFile(
    "TMP${
        SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    }_56ji5", ".jpg",
    getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
)


fun Context.checkForConnection56ji5(): Boolean {
    val connectivityManager56ji5 = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val networkCapabilities56ji5 = connectivityManager56ji5.getNetworkCapabilities(connectivityManager56ji5.activeNetwork) ?: return false
        return networkCapabilities56ji5.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        for (network56ji5 in connectivityManager56ji5.allNetworks) {
            connectivityManager56ji5.getNetworkInfo(network56ji5)?.let {
                if (it.isConnected) return true
            }
        }
        return false
    }
}

fun AppCompatActivity.checkConnectionCycle56ji5() {
    lifecycleScope.launch {
        while (isActive) {
            delay(500)
            if (isDismissed56ji5) {
                if (!checkForConnection56ji5()) {
                    InternetChecker56ji5().apply {
                        show(
                            supportFragmentManager,
                            "internet_checker_56ji5"
                        )
                    }
                    isDismissed56ji5 = false
                }
            }
        }

    }
}



