package com.nisanth.cinergy.data.api.service

import com.nisanth.cinergy.data.api.response.ResLogin
import com.nisanth.cinergy.data.api.response.ResToken
import com.nisanth.cinergy.di.utility.ApiUrls.GUEST_TOKEN
import com.nisanth.cinergy.di.utility.ApiUrls.LOGIN
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticationApi {

    @POST(GUEST_TOKEN)
    suspend fun getGuestToken(
        @Query("secret_key") secretKey: String, @Query("device_type") deviceType: String,
        @Query("device_id") deviceId: String, @Query("push_token") pushToken: String,
    ): Response<ResToken>

    @POST(LOGIN)
    suspend fun doLogin(
        @Query("device_type") deviceType: String,
        @Query("device_id") deviceId: String,
        @Query("login_type") loginType: String,
        @Header("Authorization") authorization: String,
    ): Response<ResLogin>

}