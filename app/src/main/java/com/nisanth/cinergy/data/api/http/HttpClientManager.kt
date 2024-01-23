package com.nisanth.cinergy.data.api.http

import android.content.SharedPreferences
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.nisanth.cinergy.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

interface HttpClientManager {  //  Not used

    var client: OkHttpClient
    var gsonConverterFactory: GsonConverterFactory
    var retrofit: Retrofit
}

inline fun <reified T> HttpClientManager.createApi(): T {
    return this.retrofit.create()
}

private class HttpClientManagerImpl(var sharedPreferences: SharedPreferences) : HttpClientManager {

    override var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            it.proceed(
                it.request().newBuilder().addHeader(
                    API_SECRET_KEY,
                    "accessToken"
                ).build()
            )
        }.apply {
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(StethoInterceptor())
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                addInterceptor(httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }
        .callTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

    override var gsonConverterFactory: GsonConverterFactory =
        GsonConverterFactory.create(GsonBuilder().create())

    override var retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(client)
        .build()

    companion object {
        const val API_SECRET_KEY = "secret_key"
    }
}