package com.fcmb.sampletestingappwithcicd

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    init {
        System.loadLibrary("keys")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.basekey).text = getBaseApi()
        findViewById<TextView>(R.id.facebook_key).text = getFacebookApiKey()
        val getFacebookApiKey: String = String(Base64.decode(getFacebookApiKey(), Base64.DEFAULT))
        val getBaseApi: String = String(Base64.decode(getBaseApi(), Base64.DEFAULT))

        Log.d("Keysiioi", "onCreate:---getFacebookApiKey----- $getFacebookApiKey")
        Log.d("Keysiioi", "onCreate:---getBaseApi----- $getBaseApi")
    }

    external fun getFacebookApiKey(): String?
    external fun getBaseApi(): String?
}
