package com.fcmb.sampletestingappwithcicd.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val bvn: String,
    val dateOfBirth: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val registeredAt: String
)