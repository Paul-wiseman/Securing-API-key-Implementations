package com.fcmb.sampletestingappwithcicd.data.service

import com.fcmb.sampletestingappwithcicd.data.models.UserModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyOnBoardingService {
    @GET("/v1/phoneNumber")
    suspend fun verifyUserBvn(
        @Query("phoneNumber") phoneNumber: String
    ): UserModel
}
