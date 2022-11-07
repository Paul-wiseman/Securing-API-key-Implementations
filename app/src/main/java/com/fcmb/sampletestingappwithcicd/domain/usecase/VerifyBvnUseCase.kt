package com.fcmb.sampletestingappwithcicd.domain.usecase

import com.fcmb.sampletestingappwithcicd.BuildConfig
import com.fcmb.sampletestingappwithcicd.data.models.UserDomainModel
import com.fcmb.sampletestingappwithcicd.domain.repository.OnBoardingRepository
import com.fcmb.sampletestingappwithcicd.responsewrapper.Resource

class VerifyBvnUseCase(private val repository: OnBoardingRepository) {

    val secretKey = BuildConfig.CONSUMER_KEY

    suspend operator fun invoke(phoneNumber: String): Resource<UserDomainModel> {
        return repository.verifyUserBvn(phoneNumber)
        // inside of any of your application's code
    }
}
