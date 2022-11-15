package com.fcmb.sampletestingappwithcicd.encryption

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class EncryptedSharedPref(private val context: Context) {

    private val keyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
//    val key = context.getColor(R.string.parse_application_id)
//    val advancedSpec = KeyGenParameterSpec.Builder(
//        "master_key",
//        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
//    ).apply {
//        setBlockModes(KeyProperties.BLOCK_MODE_GCM)
//        setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
//        setKeySize(256)
//        setUserAuthenticationRequired(true)
//    }.build()
//
//    private val advanceKeyAlias = MasterKeys.getOrCreate(advancedSpec)

    private val pref = EncryptedSharedPreferences.create(
        "my_secret_prefs",
        keyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val editor: SharedPreferences.Editor = pref.edit()

    fun saveName(name: String) {
        Log.d("SecondActivity", "saveName:-----$name ")
        editor.putString("Name", name).apply()
    }

    fun getName(): String? {
        Log.d("SecondActivity", "getName:-----${pref.getString("Name", "")} ")
        return pref.getString("Name", "")
    }
}
