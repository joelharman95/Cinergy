package com.nisanth.cinergy.data.repository

import com.nisanth.cinergy.data.api.response.ResEscapeRoom
import com.nisanth.cinergy.data.api.response.ResMovieInfo
import com.nisanth.cinergy.data.api.service.EscapeRoomApi
import com.nisanth.cinergy.di.utility.Constants
import com.nisanth.cinergy.di.utility.DeviceId.DEVICE_ID
import com.nisanth.cinergy.di.utility.OnError
import com.nisanth.cinergy.di.utility.OnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface EscapeRoomRepository {
    suspend fun getEscapeRoomMovies(
        memberId: String,
        userId: String, authorization: String,
        onSuccess: OnSuccess<ResEscapeRoom>,
        onError: OnError<String>
    )

    suspend fun getMovieInfo(
        movieId: String, authorization: String, userId: String,
        onSuccess: OnSuccess<ResMovieInfo>,
        onError: OnError<String>
    )
}

class EscapeRoomRepositoryImpl @Inject constructor(private val escapeRoomApi: EscapeRoomApi) :
    EscapeRoomRepository {

    override suspend fun getEscapeRoomMovies(
        memberId: String,
        userId: String, authorization: String,
        onSuccess: OnSuccess<ResEscapeRoom>,
        onError: OnError<String>
    ) {
        try {
            val response = escapeRoomApi.getEscapeRoomMovies(
                deviceType = "2",
                deviceId = DEVICE_ID,
                locationId = "5",
                memberId = memberId,
                userId = userId,
                authorization = authorization
            )
            if (response.isSuccessful) withContext(Dispatchers.Main) { response.body()?.let { onSuccess.invoke(it) } }
            else {
                withContext(Dispatchers.Main) { onError.invoke(response.message()) }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { onError.invoke(e.toString()) }
        }
    }

    override suspend fun getMovieInfo(
        movieId: String, authorization: String, userId: String,
        onSuccess: OnSuccess<ResMovieInfo>,
        onError: OnError<String>
    ) {
        try {
            val response = escapeRoomApi.getMovieInfo(
                deviceType = "2", deviceId = DEVICE_ID,
                movieId = movieId, locationId = "5",
                headerMap = getHeaderMap(authorization, userId),
//                authorization = authorization, userId = userId
            )
            if (response.isSuccessful) withContext(Dispatchers.Main) { response.body()?.let { onSuccess.invoke(it) } }
            else {
                withContext(Dispatchers.Main) { onError.invoke(response.message()) }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) { onError.invoke(e.toString()) }
        }
    }

    private fun getHeaderMap(authorization: String, userId: String): Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Authorization"] = authorization
        headerMap["userid"] = userId
        return headerMap
    }

}