package com.fcmb.sampletestingappwithcicd.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcmb.sampletestingappwithcicd.data.models.UserDomainModel
import com.fcmb.sampletestingappwithcicd.domain.usecase.VerifyBvnUseCase
import com.fcmb.sampletestingappwithcicd.responsewrapper.Resource
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val verifyBvnUseCase: VerifyBvnUseCase) : ViewModel() {

    private val _verifyBvnResponse = MutableLiveData<Resource<UserDomainModel>>()
    private val verifyBvnResponse get() = _verifyBvnResponse

    fun verifyBvn(phoneNumber: String) {
        viewModelScope.launch {
            _verifyBvnResponse.value = verifyBvnUseCase.invoke(phoneNumber)
        }
    }
}
