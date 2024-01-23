package com.nisanth.cinergy.di.utility

import kotlin.random.Random

object Constants {

}

object Pref {
    const val CINERGY_SHARED_PREFERENCES = "cinergy_shared_preferences"
}

object ApiUrls {
    const val GUEST_TOKEN = "guestToken"
    const val LOGIN = "login"
    const val ESCAPE_ROOM_MOVIES = "escapeRoomMovies"
    const val GET_MOVIE_INFO = "getMovieInfo"
}

object BundleConstants {
    const val MEMBER_ID = "memberId"
    const val USER_ID = "userId"
    const val AUTHORIZATION = "authorization"

    const val MOVIE_ID = "movieId"
}

object DeviceId {
    val DEVICE_ID = "CI -${System.currentTimeMillis()}"  //  Or Firebase ID
//    val DEVICE_ID = "CI -Cinergy${Random.nextBits(999)}"  //  Or Firebase ID
}