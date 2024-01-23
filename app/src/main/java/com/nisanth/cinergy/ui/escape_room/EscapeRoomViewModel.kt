package com.nisanth.cinergy.ui.escape_room

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisanth.cinergy.data.api.response.EscapeRoomsMovies
import com.nisanth.cinergy.data.api.response.MovieInfo
import com.nisanth.cinergy.data.api.response.Sessions
import com.nisanth.cinergy.data.api.response.ShowDateWithTimes
import com.nisanth.cinergy.data.repository.EscapeRoomRepository
import com.nisanth.cinergy.di.isSuccess
import com.nisanth.cinergy.di.utility.EscapeRoomListener
import com.nisanth.cinergy.di.utility.ShowDateTimeListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EscapeRoomViewModel @Inject constructor(
    private val escapeRoomRepository: EscapeRoomRepository
) : ViewModel() {

    private val _escapeRoomLiveData: MutableLiveData<List<EscapeRoomsMovies>> = MutableLiveData(emptyList())
    val escapeRoomLiveData: LiveData<List<EscapeRoomsMovies>> = _escapeRoomLiveData

    private val _resMovieInfo: MutableLiveData<MovieInfo> = MutableLiveData(MovieInfo())
    val resMovieInfo: LiveData<MovieInfo> = _resMovieInfo

    private val _showDateAndTime: MutableLiveData<List<ShowDateWithTimes>> = MutableLiveData(emptyList())
    val showDateAndTime: LiveData<List<ShowDateWithTimes>> = _showDateAndTime

    private val _showTimeWrtDate: MutableLiveData<List<Sessions>> = MutableLiveData(emptyList())
    val showTimeWrtDate: LiveData<List<Sessions>> = _showTimeWrtDate

    private val _openDetailView: MutableLiveData<Boolean> = MutableLiveData(false)
    val openDetailView: LiveData<Boolean> = _openDetailView

    private val _openBookingScreen: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val openBookingScreen: StateFlow<Boolean> = _openBookingScreen

    private val _showToast: MutableStateFlow<String?> = MutableStateFlow(null)
    val showToast: StateFlow<String?> = _showToast

    private val _showProgress: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showProgress: StateFlow<Boolean> = _showProgress

    private val _escapeRoomsMovies: MutableStateFlow<EscapeRoomsMovies?> = MutableStateFlow(null)
    val escapeRoomsMovies: StateFlow<EscapeRoomsMovies?> = _escapeRoomsMovies

    var erTickets: String? = null
    private var userId: String = ""
    private var authorization: String = ""

    val escapeRoomListener = object : EscapeRoomListener {
        override fun escapeRoomDetail(escapeRoomsMovies: EscapeRoomsMovies) {
            _escapeRoomsMovies.value = escapeRoomsMovies
            _openDetailView.value = true
        }
    }

    val showDateTimeListener = object : ShowDateTimeListener {
        override fun showTimeWrtDate(showDateWithTimes: ShowDateWithTimes) {
            _showTimeWrtDate.value = showDateWithTimes.sessions as List<Sessions>?

//            _showDateAndTime.postValue(it.movieInfo?.showTimes as List<ShowDateWithTimes>?)
//            _showTimeWrtDate.postValue(it.movieInfo?.showTimes?.get(0)?.sessions as List<Sessions>?)

        }
    }

    fun getEscapeRoomMovies(memberId: String, userId: String, authorization: String) {
        this.userId = userId
        this.authorization = authorization
        viewModelScope.launch(Dispatchers.IO) {
            _showProgress.value = true
            escapeRoomRepository.getEscapeRoomMovies(
                memberId = memberId,
                userId = userId,
                authorization = authorization,
                onSuccess = {
                    it.response?.let { resp ->
                        if (resp.isSuccess()) {
                            erTickets = it.erTickets
                            it.escapeRoomsMovies?.let { movies ->
                                _escapeRoomLiveData.value = movies as List<EscapeRoomsMovies>
                            }
                        }
                    }
                    _showProgress.value = false
                }, onError = {
                    _showToast.value = it
                    _showProgress.value = false
                })
        }
    }

    fun getMovieInfo(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _showProgress.value = true
            escapeRoomRepository.getMovieInfo(
                movieId = movieId,
                userId = userId,
                authorization = authorization,
                onSuccess = {
                    it.response?.let { resp ->
                        if (resp.isSuccess()) {
                            it.movieInfo?.let { movieInfo ->
                                _resMovieInfo.postValue(movieInfo)
                            }
                            _showDateAndTime.postValue(it.movieInfo?.showTimes as List<ShowDateWithTimes>?)
                            _showTimeWrtDate.postValue(it.movieInfo?.showTimes?.get(0)?.sessions as List<Sessions>?)
                        }
                    }
                    _showProgress.value = false
                }, onError = {
                    _showToast.value = it
                    _showProgress.value = false
                })
        }
    }

    fun closeDetailDialogIfItsOpen(view: View) {
        _openDetailView.value = false
    }

    fun openBooking(view: View) {
        _openBookingScreen.value = true
    }

    fun resetNavigation() {
        _openBookingScreen.value = false
    }

}