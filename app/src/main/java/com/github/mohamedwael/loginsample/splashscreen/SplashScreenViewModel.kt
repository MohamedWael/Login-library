package com.github.mohamedwael.loginsample.splashscreen

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mohamedwael.loginsample.SPLASH_SCREEN_DURATION

class SplashScreenViewModel : ViewModel() {
    private val handler = Handler()
    val navigateNext = MutableLiveData<Boolean>()

    fun startNavigation() {
        handler.postDelayed(::fireNav, SPLASH_SCREEN_DURATION)
    }

    private fun fireNav() {
        navigateNext.value = true
    }

    override fun onCleared() {
        handler.removeCallbacks(::fireNav)
        super.onCleared()
    }
}
