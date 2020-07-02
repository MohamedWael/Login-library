package com.github.mohamedwael.login.base

import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.login.config.InputValidationProvider

open class UsernameBaseViewModel(inputValidationProvider: InputValidationProvider) :
    BaseLoginViewModel(inputValidationProvider) {
    open val userName = MutableLiveData<String>()
    open val userNameError = MutableLiveData<Int?>()

    open fun isUsernameValid() = inputValidationProvider.isEmailValid(userName.value)
            || inputValidationProvider.isUsernameValid(userName.value)

}