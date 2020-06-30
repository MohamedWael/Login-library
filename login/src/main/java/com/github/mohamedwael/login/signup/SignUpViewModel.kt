package com.github.mohamedwael.login.signup

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.UsernameBaseViewModel
import com.github.mohamedwael.login.config.BROADCAST_ACTION_TYPE
import com.github.mohamedwael.login.config.BROADCAST_ACTION_TYPE_SIGN_UP
import com.github.mohamedwael.login.config.BROADCAST_SUCCESS_ACTION_DATA
import com.github.mohamedwael.login.config.InputValidationProvider
import java.io.Serializable

open class SignUpViewModel(
    private val signUp: SignUp,
    inputValidationProvider: InputValidationProvider
) : UsernameBaseViewModel(inputValidationProvider) {

    val password = MutableLiveData<String>()
    val passwordError = MutableLiveData<Int?>()

    val firstName = MutableLiveData<String>()
    val firstNameError = MutableLiveData<Int?>()
    val lastName = MutableLiveData<String>()
    val lastNameError = MutableLiveData<Int?>()
    val phone = MutableLiveData<String>()
    val phoneError = MutableLiveData<Int?>()

    var onSignUpSuccessLiveData = MutableLiveData<Bundle>()


    fun onTermsClick() {
        signUp.onTermsClick()
    }

    fun onSignUpClick() {
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
            signUp.signUp(
                userName.value!!,
                password.value!!,
                {
                    showProgressDialog(false)
                    onSignUpSuccessLiveData.value = Bundle().apply {
                        putString(BROADCAST_ACTION_TYPE, BROADCAST_ACTION_TYPE_SIGN_UP)
                        when (it) {
                            is Serializable -> putSerializable(BROADCAST_SUCCESS_ACTION_DATA, it)
                            is Parcelable -> putParcelable(BROADCAST_SUCCESS_ACTION_DATA, it)
                        }
                    }
                }, {
                    showProgressDialog(false)
                    showMessage(it)
                })
        } else {
            passwordError.value = R.string.password_not_valid
        }
    }

}
