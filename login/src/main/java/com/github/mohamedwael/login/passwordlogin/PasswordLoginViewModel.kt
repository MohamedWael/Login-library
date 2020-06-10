package com.github.mohamedwael.login.passwordlogin

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.LoginScenarioBaseViewModel
import com.github.mohamedwael.login.config.InputValidationProvider
import com.github.mohamedwael.login.verificationcodelogin.usernamevalidation.UsernameValidationViewModelFactory
import java.io.Serializable


open class PasswordLoginViewModel(
    inputValidationProvider: InputValidationProvider,
    private val passwordLogin: PasswordLogin
) : LoginScenarioBaseViewModel(inputValidationProvider) {

    val password = MutableLiveData<String>()
    val passwordError = MutableLiveData<Int?>()
    val verificationLoginButtonVisibility =
        ObservableInt(if (UsernameValidationViewModelFactory.isInitialized()) View.VISIBLE else View.GONE)
    var onVerificationLoginClick: (() -> Unit)? = null
    var onLoginSuccessLiveData = MutableLiveData<Bundle>()

    override fun onLoginClick() {
        hideKeyboard()
        if (isUsernameValid()) {
            userNameError.value = null
            validatePassword()
        } else {
            userNameError.value = R.string.username_not_valid
        }
    }

    private fun validatePassword() {
        val isPasswordValid = inputValidationProvider.isPasswordValid(password.value)
        if (isPasswordValid) {
            passwordError.value = null
            showProgressDialog(true)
            passwordLogin.login<Serializable>(
                userName.value!!,
                password.value!!,
                {
                    showProgressDialog(false)
                    onLoginSuccessLiveData.value = Bundle().apply {
                        putSerializable(BROADCAST_DATA_LOGIN_SUCCESS, it)
                    }
                }, {
                    showProgressDialog(false)
                    showMessage(it)
                })
        } else {
            passwordError.value = R.string.password_not_valid
        }
    }

    override fun onCleared() {
        onVerificationLoginClick = null
        super.onCleared()
    }
}
