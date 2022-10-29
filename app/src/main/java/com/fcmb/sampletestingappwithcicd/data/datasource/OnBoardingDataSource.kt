package com.fcmb.sampletestingappwithcicd.data.datasource

import com.fcmb.sampletestingappwithcicd.data.mappers.DomainUserMapper
import com.fcmb.sampletestingappwithcicd.data.models.UserDomainModel
import com.fcmb.sampletestingappwithcicd.data.service.DummyOnBoardingService
import com.fcmb.sampletestingappwithcicd.domain.repository.OnBoardingRepository
import com.fcmb.sampletestingappwithcicd.exceptions.HandleApiException
import com.fcmb.sampletestingappwithcicd.responsewrapper.Resource

class OnBoardingDataSource(private val service: DummyOnBoardingService) :
    OnBoardingRepository,
    HandleApiException {

    override suspend fun verifyUserBvn(phoneNumber: String): Resource<UserDomainModel> =
        safeApiCall {
            DomainUserMapper().map(service.verifyUserBvn(phoneNumber))
        }
}
