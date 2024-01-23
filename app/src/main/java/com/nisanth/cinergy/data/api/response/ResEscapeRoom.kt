package com.nisanth.cinergy.data.api.response

import com.google.gson.annotations.SerializedName

data class ResEscapeRoom(
    @SerializedName("er_tickets") val erTickets: String? = null,
    @SerializedName("response") val response: String? = null,
    @SerializedName("escape_rooms_movies") val escapeRoomsMovies: List<EscapeRoomsMovies?>? = null
)

data class EscapeRoomsMovies(
    @SerializedName("RunTime") val runTime: String? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("Rating") val rating: String? = null,
    @SerializedName("Title") val title: String? = null,
    @SerializedName("ID") val iD: String? = null,
    @SerializedName("Synopsis") val synopsis: String? = null,
    @SerializedName("ScheduledFilmId") val scheduledFilmId: String? = null,
    @SerializedName("slug") val slug: String? = null,
    @SerializedName("image_url_poster") val imageUrlPoster: String? = null
)
