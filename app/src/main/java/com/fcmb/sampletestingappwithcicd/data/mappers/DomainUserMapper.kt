package com.fcmb.sampletestingappwithcicd.data.mappers

import com.fcmb.businesszone_mobileapplication.util.basemapper.BaseMapper
import com.fcmb.sampletestingappwithcicd.data.models.UserDomainModel
import com.fcmb.sampletestingappwithcicd.data.models.UserModel

class DomainUserMapper : BaseMapper<UserModel, UserDomainModel> {
    override fun map(input: UserModel): UserDomainModel {
        return UserDomainModel(
            firstName = input.firstName,
            lastName = input.lastName,
            bvn = input.bvn
        )
    }
}
