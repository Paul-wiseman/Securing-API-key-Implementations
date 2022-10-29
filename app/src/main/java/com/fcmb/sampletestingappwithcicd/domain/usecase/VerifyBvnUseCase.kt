package com.fcmb.sampletestingappwithcicd.domain.usecase

import com.fcmb.sampletestingappwithcicd.data.models.UserDomainModel
import com.fcmb.sampletestingappwithcicd.domain.repository.OnBoardingRepository
import com.fcmb.sampletestingappwithcicd.responsewrapper.Resource

class VerifyBvnUseCase(private val repository: OnBoardingRepository) {
    suspend operator fun invoke(phoneNumber: String): Resource<UserDomainModel> {
        return repository.verifyUserBvn(phoneNumber)
    }
}