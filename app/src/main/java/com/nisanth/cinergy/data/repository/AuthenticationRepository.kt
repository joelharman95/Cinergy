package com.nisanth.cinergy.data.repository

import com.nisanth.cinergy.BuildConfig
import com.nisanth.cinergy.data.api.response.ResLogin
import com.nisanth.cinergy.data.api.response.ResToken
import com.nisanth.cinergy.data.api.service.AuthenticationApi
import com.nisanth.cinergy.di.utility.DeviceId.DEVICE_ID
import com.nisanth.cinergy.di.utility.OnError
import com.nisanth.cinergy.di.utility.OnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

interface AuthenticationRepository {
    //    suspend fun <T> getToken(): Result<T>
    suspend fun getToken(onSuccess: OnSuccess<ResToken>, onError: OnError<String>)
    suspend fun doLogin(
        authorization: String,
        onSuccess: OnSuccess<ResLogin>,
        onError: OnError<String>
    )
}

class AuthenticationRepositoryImpl @Inject constructor(private val authenticationApi: AuthenticationApi) :
    AuthenticationRepository {

    override suspend fun getToken(onSuccess: OnSuccess<ResToken>, onError: OnError<String>) {
        try {
            val response = authenticationApi.getGuestToken(
                secretKey = BuildConfig.SECRET_KEY,
                deviceType = "2", deviceId = DEVICE_ID,
                ""
            )
            if (response.isSuccessful)
                withContext(Dispatchers.Main) { response.body()?.let { onSuccess.invoke(it) } }
            else {
                withContext(Dispatchers.Main) { onError.invoke(response.message()) }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { onError.invoke(e.toString()) }
        }
    }

    override suspend fun doLogin(
        authorization: String,
        onSuccess: OnSuccess<ResLogin>,
        onError: OnError<String>
    ) {
        try {
            val response = authenticationApi.doLogin(
                deviceType = "2", deviceId = DEVICE_ID,
                loginType = "2", authorization = authorization,
            )
            if (response.isSuccessful)
                withContext(Dispatchers.Main) { response.body()?.let { onSuccess.invoke(it) } }
            else {
                withContext(Dispatchers.Main) { onError.invoke(response.message()) }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { onError.invoke(e.toString()) }
        }
    }

}