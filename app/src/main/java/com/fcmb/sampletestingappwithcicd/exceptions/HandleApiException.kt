package com.fcmb.sampletestingappwithcicd.exceptions

import android.accounts.NetworkErrorException
import com.fcmb.sampletestingappwithcicd.responsewrapper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface HandleApiException {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        // val errorMessage = getErrorApiError(throwable)
                        Resource
                            .Failure("Error ${throwable.code()}: Unable to connect to the server")
                    }

                    is NetworkErrorException -> {
                        Resource.Failure("Check your connection!")
                    }

                    else -> {
                        Resource.Failure(throwable.message.toString())
                    }
                }
            }
        }
    }
}
