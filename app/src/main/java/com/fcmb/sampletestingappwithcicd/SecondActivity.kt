package com.fcmb.sampletestingappwithcicd

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fcmb.sampletestingappwithcicd.encryption.EncryptedSharedPref
import com.fcmb.sampletestingappwithcicd.encryption.EncryptingFilesUtils

class SecondActivity : AppCompatActivity() {
    private lateinit var encryptedSharedPreferences: EncryptedSharedPref
    private lateinit var encryptedFile: EncryptingFilesUtils
    private lateinit var etName: EditText
    private lateinit var btnSave: Button
    private lateinit var btnSaveFile: Button
    private lateinit var btnRevealSaveFile: Button
    private lateinit var btnReveal: Button
    private lateinit var tvShow: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        encryptedSharedPreferences = EncryptedSharedPref(this)
        encryptedFile = EncryptingFilesUtils(this)

        etName = findViewById(R.id.etName)
        btnSave = findViewById(R.id.button_save)
        btnSaveFile = findViewById(R.id.btn_save_file)
        btnRevealSaveFile = findViewById(R.id.btn_reveal_saved_files)
        btnReveal = findViewById(R.id.button_reveal)
        tvShow = findViewById(R.id.showName)

        // encrypted shared preference
        btnSave.setOnClickListener {
            val name = etName.text.toString()
            encryptedSharedPreferences.saveName(name)
            etName.setText(null)
        }

        btnReveal.setOnClickListener {
            val result = encryptedSharedPreferences.getName()
            Log.d("SecondActivity", "onCreate: saved stuff from pref--------- $result ")
            tvShow.text = result
        }

        // encrypted files
        btnSaveFile.setOnClickListener {
            val name = etName.text.toString().toByteArray()
            Log.d("SecondActivity", "onCreate: name as byte array--------- $name ")
            val result = encryptedFile.writeToFile(name)
            tvShow.text = null
        } // encrypted files

        btnRevealSaveFile.setOnClickListener {
            val result = encryptedFile.readFromFile()

            Log.d("SecondActivity", "onCreate: getting saved file data--------- $result ")
            tvShow.text = result.decodeToString()
        }
    }
}
