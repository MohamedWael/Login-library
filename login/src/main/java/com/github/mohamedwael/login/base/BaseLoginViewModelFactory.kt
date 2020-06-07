package com.github.mohamedwael.login.base

import androidx.lifecycle.ViewModelProvider
import com.github.mohamedwael.login.config.InputValidationProvider

abstract class BaseLoginViewModelFactory : ViewModelProvider.Factory {
    lateinit var inputValidationProvider: InputValidationProvider

    fun isInitialized() = ::inputValidationProvider.isInitialized

    fun injectInputValidationProvider(inputValidationProvider: InputValidationProvider) {
        this.inputValidationProvider = inputValidationProvider
    }
}