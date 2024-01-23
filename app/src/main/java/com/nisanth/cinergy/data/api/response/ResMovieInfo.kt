package com.nisanth.cinergy.data.api.response

import com.google.gson.annotations.SerializedName

data class ResMovieInfo(
    @SerializedName("movie_info") val movieInfo: MovieInfo? = null,
    @SerializedName("er_tickets") val erTickets: String? = null,
    @SerializedName("upcoming_movies") val upcomingMovies: List<UpcomingMovies?>? = null,
    @SerializedName("response") val response: String? = null,
    @SerializedName("grouped_movies") val groupedMovies: List<Any?>? = null
)

data class UpcomingMovies(
    @SerializedName("RunTime") val runTime: String? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("Rating") val rating: String? = null,
    @SerializedName("Title") val title: String? = null,
    @SerializedName("Genres") val genres: String? = null,
    @SerializedName("Synopsis") val synopsis: String? = null,
    @SerializedName("ScheduledFilmId") val scheduledFilmId: String? = null,
    @SerializedName("slug") val slug: String? = null,
    @SerializedName("image_url_poster") val imageUrlPoster: String? = null,
    @SerializedName("TrailerUrl") val trailerUrl: String? = null
)

data class MovieInfo(
    @SerializedName("coming_soon_reminder") val comingSoonReminder: Int? = null,
    @SerializedName("RunTime") val runTime: String? = null,
    @SerializedName("sale_on") val saleOn: Any? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("Rating") val rating: String? = null,
    @SerializedName("show_times") val showTimes: List<ShowDateWithTimes?>? = null,
    @SerializedName("date_list") val dateList: List<String?>? = null,
    @SerializedName("Title") val title: String? = null,
    @SerializedName("Synopsis") val synopsis: String? = null,
    @SerializedName("cast_and_crew") val castAndCrew: List<Any?>? = null,
    @SerializedName("Genres") val genres: String? = null,
    @SerializedName("ScheduledFilmId") val scheduledFilmId: String? = null,
    @SerializedName("slug") val slug: String? = null,
    @SerializedName("image_url_poster") val imageUrlPoster: String? = null,
    @SerializedName("TrailerUrl") val trailerUrl: String? = null
)

data class ShowDateWithTimes(
    @SerializedName("date") val date: String? = null,
    @SerializedName("sessions") val sessions: List<Sessions?>? = null,
    var isHighLighted: Boolean = false //  For highlighting the selected date
)

data class Sessions(
    @SerializedName("AllowTicketSales") val allowTicketSales: Boolean? = null,
    @SerializedName("SoldoutStatus") val soldoutStatus: Int? = null,
    @SerializedName("Attribute") val attribute: String? = null,
    @SerializedName("IsAllocatedSeating") val isAllocatedSeating: Boolean? = null,
    @SerializedName("SeatsAvailable") val seatsAvailable: Int? = null,
    @SerializedName("ScreenNumber") val screenNumber: Int? = null,
    @SerializedName("ShowtimeVista") val showtimeVista: String? = null,
    @SerializedName("Showtime") val showtime: String? = null,
    @SerializedName("SessionId") val sessionId: String? = null,
    @SerializedName("ScheduledFilmId") val scheduledFilmId: String? = null
)