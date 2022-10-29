package com.fcmb.sampletestingappwithcicd.onboarding.data.datasource

import com.fcmb.sampletestingappwithcicd.data.models.UserModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient


object Fake {

    val contentType = "application/json".toMediaType()

    val jsonConverter = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    val okHttpClient = OkHttpClient.Builder()
        .build()

    fun getSampleJsonResponse(): String {
        return Json.encodeToString(
            UserModel(
                firstName = "Paul",
                lastName = "Nero",
                bvn = "28836993739",
                email = "Paul.Nero@fcmb.com",
                dateOfBirth = "25/6/2022",
                id = 299434,
                registeredAt = "3/2/2030"
            )
        )

    }
}