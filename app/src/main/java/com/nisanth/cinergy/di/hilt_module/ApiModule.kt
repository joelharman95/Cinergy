package com.nisanth.cinergy.di.hilt_module

import com.nisanth.cinergy.data.api.service.AuthenticationApi
import com.nisanth.cinergy.data.api.service.EscapeRoomApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
class ApiModule {

    @Provides
    @ActivityRetainedScoped
    fun getAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @ActivityRetainedScoped
    fun getEscapeRoomApi(retrofit: Retrofit): EscapeRoomApi {
        return retrofit.create(EscapeRoomApi::class.java)
    }

}