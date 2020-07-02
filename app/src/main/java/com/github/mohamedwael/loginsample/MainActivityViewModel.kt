package com.github.mohamedwael.loginsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.blogspot.mowael.utilslibrary.Logger
import com.github.mohamedwael.login.config.LoginConfig
import com.github.mohamedwael.login.createpassword.CreatePasswordConfig
import com.github.mohamedwael.login.forgetpassword.ForgetPasswordConfig
import com.github.mohamedwael.login.verificationcodelogin.verification.VerificationConfig
import com.github.mohamedwael.loginsample.authorization.AppInputValidationProvider
import com.github.mohamedwael.loginsample.authorization.PasswordLoginImpl
import com.github.mohamedwael.loginsample.authorization.SignUpImpl
import com.github.mohamedwael.loginsample.authorization.VerificationProviderImpl

class MainActivityViewModel : ViewModel() {
    val showMessageLiveData = MutableLiveData<String>()
    lateinit var navController: NavController

    fun prepareLogin() {
        LoginConfig.Builder(AppInputValidationProvider())
            .usernameValidation(VerificationProviderImpl())
            .createPassword(getCreatePasswordConfig())
            .verificationLogin(getVerificationConfig())
            .forgetPassword(ForgetPasswordConfig { Logger.d("forget password username: $it") })
            .signUp(SignUpImpl({
                showMessageLiveData.value = "onTermsAndConditionsClick"
            }, {
                showMessageLiveData.value = "onPrivacyPolicyClick"
            }))
            .passwordLogin(PasswordLoginImpl())
            .build()
    }

    private fun getVerificationConfig() = VerificationConfig(
        onPinCodeChangeListener = { username, pinCode ->
            username?.also { Logger.d("onPinCodeChangeListener username: $it") }
            Logger.d("onPinCodeChangeListener code: $pinCode")

            navController.navigate(R.id.action_verificationLoginFragment_to_createPasswordFragment)
        }, onTimerClick = {
            Logger.d("timer click")
        }, onSendClick = {
            Logger.d("send click")
        }, onTimerStarted = {
            Logger.d("onTimerStarted")
        }, onTimerTickDownFinish = {
            Logger.d("onTimerTickDownFinish")
        })


    private fun getCreatePasswordConfig() = CreatePasswordConfig(
        onPasswordMatch = { password, confirmPassword, isPasswordMatches ->
            Logger.d("password: $password, confirmPassword: $confirmPassword, isPasswordMatches: $isPasswordMatches")
        },
        onSendActionClick = {
            Logger.d("onSendActionClick")
        }
    )
}