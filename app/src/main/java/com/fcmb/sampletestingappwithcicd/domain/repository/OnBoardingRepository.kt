package com.fcmb.sampletestingappwithcicd.domain.repository

import com.fcmb.sampletestingappwithcicd.data.models.UserDomainModel
import com.fcmb.sampletestingappwithcicd.responsewrapper.Resource

interface OnBoardingRepository {
    suspend fun verifyUserBvn(phoneNumber: String): Resource<UserDomainModel>
}
