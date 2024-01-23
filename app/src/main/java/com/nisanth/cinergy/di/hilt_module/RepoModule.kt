package com.nisanth.cinergy.di.hilt_module

import com.nisanth.cinergy.data.api.service.AuthenticationApi
import com.nisanth.cinergy.data.api.service.EscapeRoomApi
import com.nisanth.cinergy.data.repository.AuthenticationRepository
import com.nisanth.cinergy.data.repository.AuthenticationRepositoryImpl
import com.nisanth.cinergy.data.repository.EscapeRoomRepository
import com.nisanth.cinergy.data.repository.EscapeRoomRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepoModule {

    @Provides
    @ActivityRetainedScoped
    fun getAuthenticationRepo(authenticationApi: AuthenticationApi): AuthenticationRepository {
        return AuthenticationRepositoryImpl(authenticationApi)
    }

    @Provides
    @ActivityRetainedScoped
    fun getEscapeRoomRepo(escapeRoomApi: EscapeRoomApi): EscapeRoomRepository {
        return EscapeRoomRepositoryImpl(escapeRoomApi)
    }

}