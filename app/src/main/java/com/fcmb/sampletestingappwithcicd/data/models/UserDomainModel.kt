package com.fcmb.sampletestingappwithcicd.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDomainModel(
    val firstName: String,
    val lastName: String,
    val bvn: String
)
