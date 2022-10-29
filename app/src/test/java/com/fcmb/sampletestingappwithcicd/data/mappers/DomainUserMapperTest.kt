package com.fcmb.sampletestingappwithcicd.data.mappers

import com.fcmb.sampletestingappwithcicd.data.models.UserDomainModel
import com.fcmb.sampletestingappwithcicd.data.models.UserModel
import org.assertj.core.api.Assertions
import org.junit.Test

class DomainUserMapperTest {

    @Test
    fun `return UserDomainModel when UserModel is passed as parameter`() {

        val userModel = UserModel("1234", "12/2/1819", "paul@gmail.com", "paul", 78, "Emeka", "12/4/33")

        val mapper = DomainUserMapper()

        val result = mapper.map(userModel)

        Assertions.assertThat(result).isInstanceOf(UserDomainModel::class.java)
        Assertions.assertThat(result.bvn).isEqualTo("1234")
    }
}
