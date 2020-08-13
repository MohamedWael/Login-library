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
    val isDataValid = mutableListOf<Boolean>()

    fun onTermsAndConditionsClick() {
        signUp.onTermsAndConditionsClick()
    }

    fun onPrivacyPolicyClick() {
        signUp.onPrivacyPolicyClick()
    }

    open fun onSignUpClick() {
        hideKeyboard()

        fun isInvalidName(name: String?) = TextUtils.isEmpty(name)
        firstNameError.value = if (isInvalidName(firstName.value)) {
            isDataValid.add(0, false)
            R.string.first_name_required
        } else {
            isDataValid.add(0, true)
            null
        }

        lastNameError.value = if (isInvalidName(lastName.value)) {
            isDataValid.add(1, false)
            R.string.last_name_required
        } else {
            isDataValid.add(1, true)
            null
        }

        userNameError.value = if (isUsernameValid()) {
            isDataValid.add(2, false)
            null
        } else {
            isDataValid.add(2, true)
            R.string.username_not_valid
        }
        val isPasswordValid = inputValidationProvider.isPasswordValid(password.value)
        passwordError.value = if (isPasswordValid) {
            isDataValid.add(3, false)

            null
        } else {
            isDataValid.add(3, true)
            R.string.password_not_valid
        }

        val signingUpUser = SigningUpUser(
            firstName.value ?: "",
            lastName.value ?: "",
            userName.value ?: "",
            password.value ?: "",
            mutableMapOf()
        )

        if (isDataValid.any { !it } && !onSignUpDataPrepared(signingUpUser)) {
            signUp(signingUpUser)
        }

    }

    open fun signUp(signingUpUser: SigningUpUser) {
        showProgressDialog(true)
        signUp.signUp(signingUpUser, {
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

    /**
     * Used to append extra data to the sign up data object using the extra data map
     */
    open fun onSignUpDataPrepared(signUp: SigningUpUser): Boolean = true

}
