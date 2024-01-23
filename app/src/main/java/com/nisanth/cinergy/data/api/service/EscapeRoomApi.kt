package com.nisanth.cinergy.data.api.service

import com.nisanth.cinergy.data.api.response.ResEscapeRoom
import com.nisanth.cinergy.data.api.response.ResMovieInfo
import com.nisanth.cinergy.di.utility.ApiUrls.ESCAPE_ROOM_MOVIES
import com.nisanth.cinergy.di.utility.ApiUrls.GET_MOVIE_INFO
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query

interface EscapeRoomApi {

    @POST(ESCAPE_ROOM_MOVIES)
    suspend fun getEscapeRoomMovies(
        @Query("device_type") deviceType: String, @Query("device_id") deviceId: String,
        @Query("location_id") locationId: String, @Query("member_id") memberId: String,
        @Header("Authorization") authorization: String, @Header("userid") userId: String,
    ): Response<ResEscapeRoom>

    @POST(GET_MOVIE_INFO)
    suspend fun getMovieInfo(
        @Query("device_type") deviceType: String, @Query("device_id") deviceId: String,
        @Query("movie_id") movieId: String, @Query("location_id") locationId: String,
        @HeaderMap headerMap: Map<String, String>
//        @Header("Authorization") authorization: String, @Header("userid") userId: String,
    ): Response<ResMovieInfo>

}