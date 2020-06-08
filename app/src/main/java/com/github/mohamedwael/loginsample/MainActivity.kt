package com.github.mohamedwael.loginsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.blogspot.mowael.utilslibrary.Logger
import com.github.mohamedwael.login.config.LoginConfig
import com.github.mohamedwael.login.createpassword.CreatePasswordConfig
import com.github.mohamedwael.login.forgetpassword.ForgetPasswordConfig
import com.github.mohamedwael.login.verificationcodelogin.verification.VerificationConfig
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
        val createPasswordConfig = CreatePasswordConfig(
            onPasswordMatch = { password, confirmPassword, isPasswordMatches ->
                Logger.d("password: $password, confirmPassword: $confirmPassword, isPasswordMatches: $isPasswordMatches")
            },
            onSendActionClick = {
                Logger.d("onSendActionClick")
            }
        )

        val verificationConfig = VerificationConfig(
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
            })

        LoginConfig.Builder(AppInputValidationProvider())
            .addAllLoginScreen()
            .usernameValidation(VerificationProviderImpl())
            .createPassword(createPasswordConfig)
            .verificationLogin(verificationConfig)
            .forgetPassword(ForgetPasswordConfig { Logger.d("forget password username: $it") })
            .signUp()
            .passwordLogin(PasswordLoginImpl())
            .build()
    }
}