package com.fcmb.sampletestingappwithcicd.encryption

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import java.io.File

class EncryptingFilesUtils(context: Context) {
    private val keyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val secretFile = File(context.filesDir, "secret_file")

    private val encryptedFile = EncryptedFile.Builder(
        secretFile,
        context,
        keyAlias,
        EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
    ).build()

    fun writeToFile(data: ByteArray) {
        encryptedFile.openFileOutput().use { outputStream ->
            // Write data to your encrypted file
            outputStream.write(data)
        }
    }

    fun readFromFile(): ByteArray {
        val savedFile: ByteArray
        encryptedFile.openFileInput().use { inputStream ->
            // Read data from your encrypted file
            savedFile = inputStream.readBytes()
        }
        return savedFile
    }
}
