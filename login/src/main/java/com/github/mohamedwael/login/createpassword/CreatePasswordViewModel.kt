package com.github.mohamedwael.login.createpassword

import android.view.View
import androidx.databinding.Observable
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.BaseLoginViewModel
import com.github.mohamedwael.login.config.InputValidationProvider

class CreatePasswordViewModel(
    val config: CreatePasswordConfig,
    inputValidationProvider: InputValidationProvider
) :
    BaseLoginViewModel(inputValidationProvider) {
    val password = MutableLiveData<String>()
    val passwordError = MutableLiveData<Int?>()
    val confirmPassword = MutableLiveData<String>()
    val confirmPasswordError = MutableLiveData<Int?>()
    val confirmPasswordFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        if (hasFocus) {
            validatePassword()
        }
    }
    val onSendActionClick: (() -> Unit)? = {
        hideKeyboard()
        config.onSendActionClick?.invoke()
    }

    fun validatePassword() {
        password.value?.also { password ->
            isPasswordValid(password)
            if (config.confirmPasswordVisibility.get() == View.GONE) {
                config.onPasswordMatch(
                    password,
                    "",
                    inputValidationProvider.isPasswordValid(password)
                )
            }
        }
    }

    private fun isPasswordValid(password: String) {
        if (!inputValidationProvider.isPasswordValid(password)) {
            passwordError.value = R.string.password_not_valid
        } else {
            passwordError.value = null
        }
    }

    fun compareConfirmPasswordMatch() {
        password.value?.also { password ->
            isPasswordValid(password)
            if (inputValidationProvider.isPasswordValid(password)) {
                confirmPassword.value?.also { confirmPassword ->
                    config.onPasswordMatch(password, confirmPassword, password == confirmPassword)
                    if (password != confirmPassword) {
                        confirmPasswordError.value = R.string.password_doesnt_match
                    } else {
                        hideKeyboard()
                        confirmPasswordError.value = null
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
