package com.github.mohamedwael.loginsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.blogspot.mowael.utilslibrary.Logger
import com.github.mohamedwael.login.createpassword.CreatePasswordConfig
import com.github.mohamedwael.login.createpassword.CreatePasswordViewModelFactory
import com.github.mohamedwael.login.forgetpassword.ForgetPasswordViewModelFactory
import com.github.mohamedwael.login.passwordlogin.PasswordLoginViewModelFactory
import com.github.mohamedwael.login.signup.SignUpViewModelFactory
import com.github.mohamedwael.login.verificationcodelogin.usernamevalidation.UsernameValidationViewModelFactory
import com.github.mohamedwael.login.verificationcodelogin.verification.VerificationConfig
import com.github.mohamedwael.login.verificationcodelogin.verification.VerificationLoginViewModelFactory
import com.github.mohamedwael.loginsample.authorization.AppInputValidationProvider
import com.github.mohamedwael.loginsample.authorization.PasswordLoginImpl
import com.github.mohamedwael.loginsample.authorization.VerificationProviderImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareLogin()
        setContentView(R.layout.activity_main)

    }

    private fun prepareLogin() {
        val validationProvider = AppInputValidationProvider()
        UsernameValidationViewModelFactory.inject(VerificationProviderImpl(), validationProvider)

        CreatePasswordViewModelFactory.inject(
            CreatePasswordConfig(
                { password, confirmPassword, isPasswordMatches ->
                    Logger.d("password: $password, confirmPassword: $confirmPassword, isPasswordMatches: $isPasswordMatches")
                },
                onSendActionClick = {
                    Logger.d("onSendActionClick")
                }
            ), validationProvider
        )

        VerificationLoginViewModelFactory.inject(
            VerificationConfig(
                onPinCodeChangeListener = { username, pinCode ->
                    username?.also { Logger.d("onPinCodeChangeListener username: $it") }
                    Logger.d("onPinCodeChangeListener code: $pinCode")
                    Navigation.findNavController(this, R.id.loginNavHost)
                        .navigate(R.id.action_verificationLoginFragment_to_createPasswordFragment)
                }, onTimerClick = {
                    Logger.d("timer click")
                }, onSendClick = {
                    Logger.d("send click")
                }, onTimerStarted = {
                    Logger.d("onTimerStarted")
                }, onTimerTickDownFinish = {
                    Logger.d("onTimerTickDownFinish")
                }), validationProvider
        )

        ForgetPasswordViewModelFactory.injectInputValidationProvider(validationProvider)

        SignUpViewModelFactory.injectInputValidationProvider(validationProvider)

        PasswordLoginViewModelFactory.inject(
            validationProvider,
            PasswordLoginImpl()
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}