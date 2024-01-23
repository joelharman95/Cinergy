package com.nisanth.cinergy.ui.landing

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisanth.cinergy.R
import com.nisanth.cinergy.data.api.response.ResLogin
import com.nisanth.cinergy.data.api.response.ResToken
import com.nisanth.cinergy.data.api.response.User
import com.nisanth.cinergy.data.repository.AuthenticationRepository
import com.nisanth.cinergy.di.hilt_module.NetworkModule
import com.nisanth.cinergy.di.isSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _benefits: MutableStateFlow<List<String>> = MutableStateFlow(mutableListOf())
    val benefits: StateFlow<List<String>> = _benefits

    private val _navigateToEscapeRoom: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val navigateToEscapeRoom: StateFlow<Boolean> = _navigateToEscapeRoom

    private val _showToast: MutableStateFlow<String?> = MutableStateFlow(null)
    val showToast: StateFlow<String?> = _showToast

    private val _showProgress: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showProgress: StateFlow<Boolean> = _showProgress

    var user: User? = null
    var authorization: String? = null

    init {
        _benefits.value = listOf(
            "Enjoy a free popcorn on sign up.",
            "Enjoy a $5.00 Elite reward after 300 points to spend however you want",
            "Enjoy a special birthday movie ticket offer",
            "Enjoy exclusive content, sneak peaks, and special offers!",
        )
    }

    /*
    * If user goes back all the way to landing screen again
    * and continuing with same guest option
    * */
    fun resetNavigation() {
        _navigateToEscapeRoom.value = false
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSignIn, R.id.btnSignUp -> {}
            R.id.btnGuest -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _showProgress.value = true
                    authenticationRepository.getToken(onSuccess = {
                        it.response?.let { resp ->
                            if (resp.isSuccess()) {
                                it.token?.let { it1 -> doLogin(it1) }
                            }
                        }
                    }, onError = {
                        _showToast.value = it
                        _showProgress.value = false
                    })
                }
            }
        }
    }

    private fun doLogin(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authorization = "Bearer $token"
            authenticationRepository.doLogin("Bearer $token", onSuccess = {
                user = it.user
                _navigateToEscapeRoom.value = true
                _showProgress.value = false
            }, onError = {
                _showToast.value = it
                _showProgress.value = false
            })
        }
    }

}