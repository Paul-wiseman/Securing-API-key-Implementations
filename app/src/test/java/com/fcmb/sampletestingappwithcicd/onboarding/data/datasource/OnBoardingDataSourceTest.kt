package com.fcmb.sampletestingappwithcicd.onboarding.data.datasource

import com.fcmb.sampletestingappwithcicd.data.datasource.OnBoardingDataSource
import com.fcmb.sampletestingappwithcicd.data.service.DummyOnBoardingService
import com.fcmb.sampletestingappwithcicd.onboarding.data.datasource.Fake.contentType
import com.fcmb.sampletestingappwithcicd.onboarding.data.datasource.Fake.getSampleJsonResponse
import com.fcmb.sampletestingappwithcicd.onboarding.data.datasource.Fake.jsonConverter
import com.fcmb.sampletestingappwithcicd.onboarding.data.datasource.Fake.okHttpClient
import com.fcmb.sampletestingappwithcicd.responsewrapper.Resource
import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
class OnBoardingDataSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var webservice: DummyOnBoardingService
    private lateinit var dataSource: OnBoardingDataSource

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(jsonConverter.asConverterFactory(contentType))
            .build()
        webservice = retrofit.create(DummyOnBoardingService::class.java)
        dataSource = OnBoardingDataSource(webservice)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `verifyUserBvn throws an exception when response is 500 error state from the server`(): Unit =
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setBody("Internal server error")
                    .setResponseCode(500)
                    .throttleBody(1024, 1, TimeUnit.SECONDS)
            )
            val response = dataSource.verifyUserBvn("0918863837")
            assertThat(response).isInstanceOf(Resource.Failure::class.java) // on failure an instance of [Resource.Failure] is returned
            assertThat(response.message).isEqualTo("Error 500: Unable to connect to the server") // Failure message returned
            assertThat(response.data).isEqualTo(null) // because an error is returned data is null
        }

    @Test
    fun `verifyUserBvn response is a success state if request is 200`(): Unit = runBlocking {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/v1/phoneNumber" -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(getSampleJsonResponse())
                    }
                    else -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(getSampleJsonResponse())
                    }
                }
            }
        }
        val result = dataSource.verifyUserBvn("0918863837")
        assertThat(result).isInstanceOf(Resource.Success::class.java) // on success an instance of [Resource.Success] is returned
        assertThat(result.message).isEqualTo(null) // because call to the backend is successful message will be null
        assertThat(result.data?.bvn).isEqualTo("28836993739")
        assertThat(result.data?.firstName).isEqualTo("Paul")
        assertThat(result.data?.lastName).isEqualTo("Nero")
    }
}
