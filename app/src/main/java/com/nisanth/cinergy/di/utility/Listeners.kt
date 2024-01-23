package com.nisanth.cinergy.di.utility

import com.nisanth.cinergy.data.api.response.EscapeRoomsMovies
import com.nisanth.cinergy.data.api.response.ShowDateWithTimes

typealias OnSuccess<R> = (R) -> Unit
typealias OnError<R> = (R) -> Unit

interface EscapeRoomListener {
    fun escapeRoomDetail(escapeRoomsMovies: EscapeRoomsMovies)
}

interface ShowDateTimeListener {
    fun showTimeWrtDate(showDateWithTimes: ShowDateWithTimes)
}