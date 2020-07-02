package com.github.mohamedwael.login.signup

import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
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

    var onSignUpSuccessLiveData = MutableLiveData<Bundle>()


    fun onTermsAndConditionsClick() {
        signUp.onTermsAndConditionsClick()
    }

    fun onPrivacyPolicyClick() {
        signUp.onPrivacyPolicyClick()
    }

    fun onSignUpClick() {
        hideKeyboard()
        if (!isValidName()) return

        userNameError.value = if (isUsernameValid()) {
            validatePassword()
            null
        } else {
            R.string.username_not_valid
        }


    }


    private fun isValidName(): Boolean {
        var validName: Boolean
        fun isInvalidName(name: String?) = TextUtils.isEmpty(name)
        firstNameError.value = if (isInvalidName(firstName.value)) {
            validName = false
            R.string.first_name_required
        } else {
            validName = true
            null
        }

        lastNameError.value = if (isInvalidName(lastName.value)) {
            validName = false
            R.string.last_name_required
        } else {
            validName = true
            null
        }
        return validName
    }

    private fun validatePassword() {
        val isPasswordValid = inputValidationProvider.isPasswordValid(password.value)
        passwordError.value = if (isPasswordValid) {
            signUp()
            null
        } else {
            R.string.password_not_valid
        }
    }

    private fun signUp() {
        showProgressDialog(true)
        signUp.signUp(SigningUpUser(
            firstName.value!!,
            lastName.value!!,
            userName.value!!,
            password.value!!
        ), {
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
    }

}
