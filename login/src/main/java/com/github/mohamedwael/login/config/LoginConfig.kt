package com.github.mohamedwael.login.config

import com.github.mohamedwael.login.createpassword.CreatePasswordConfig
import com.github.mohamedwael.login.createpassword.CreatePasswordViewModelFactory
import com.github.mohamedwael.login.forgetpassword.ForgetPasswordConfig
import com.github.mohamedwael.login.forgetpassword.ForgetPasswordViewModelFactory
import com.github.mohamedwael.login.passwordlogin.PasswordLogin
import com.github.mohamedwael.login.passwordlogin.PasswordLoginViewModelFactory
import com.github.mohamedwael.login.signup.SignUpViewModelFactory
import com.github.mohamedwael.login.verificationcodelogin.usernamevalidation.UsernameValidationViewModelFactory
import com.github.mohamedwael.login.verificationcodelogin.usernamevalidation.VerificationProvider
import com.github.mohamedwael.login.verificationcodelogin.verification.VerificationConfig
import com.github.mohamedwael.login.verificationcodelogin.verification.VerificationLoginViewModelFactory

enum class LoginScreen {
    PHONE_PASSWORD_SCREEN,
    USERNAME_VALIDATION_SCREEN,
    VERIFICATION_CODE_SCREEN,
    FORGET_PASSWORD_SCREEN,
    SIGN_UP_SCREEN,
    CREATE_PASSWORD_SCREEN
}

class LoginConfig private constructor(builder: Builder) {
    val loginScenarios = builder.loginScenarios.toSet()
    val loginScreenFactories = mapOf(
        Pair(LoginScreen.PHONE_PASSWORD_SCREEN, PasswordLoginViewModelFactory),
        Pair(LoginScreen.FORGET_PASSWORD_SCREEN, ForgetPasswordViewModelFactory),
        Pair(LoginScreen.USERNAME_VALIDATION_SCREEN, UsernameValidationViewModelFactory),
        Pair(LoginScreen.SIGN_UP_SCREEN, SignUpViewModelFactory),
        Pair(LoginScreen.CREATE_PASSWORD_SCREEN, CreatePasswordViewModelFactory),
        Pair(LoginScreen.VERIFICATION_CODE_SCREEN, VerificationLoginViewModelFactory)
    )
    val inputValidationProvider: InputValidationProvider = builder.inputValidationProvider

    fun initPasswordLogin(passwordLogin: PasswordLogin) {
        PasswordLoginViewModelFactory.inject(passwordLogin, inputValidationProvider)
    }

    fun initForgetPassword(forgetPasswordConfig: ForgetPasswordConfig) {
        ForgetPasswordViewModelFactory.inject(forgetPasswordConfig, inputValidationProvider)
    }

    fun initUsernameValidation(verificationProvider: VerificationProvider) {
        UsernameValidationViewModelFactory.inject(verificationProvider, inputValidationProvider)
    }

    fun initCreatePassword(config: CreatePasswordConfig) {
        CreatePasswordViewModelFactory.inject(config, inputValidationProvider)
    }

    fun initSignUp() {
        SignUpViewModelFactory.injectInputValidationProvider(inputValidationProvider)
    }

    fun initVerificationLogin(verificationConfig: VerificationConfig) {
        VerificationLoginViewModelFactory.inject(verificationConfig, inputValidationProvider)
    }

    class Builder(internal val inputValidationProvider: InputValidationProvider) {

        internal val loginScenarios = mutableSetOf<LoginScreen>()
        private var passwordLogin: PasswordLogin? = null
        private var forgetPasswordConfig: ForgetPasswordConfig? = null
        private var verificationProvider: VerificationProvider? = null
        private var createPasswordConfig: CreatePasswordConfig? = null
        private var verificationConfig: VerificationConfig? = null

        fun addLoginScreen(loginScreen: LoginScreen) = apply {
            loginScenarios.add(loginScreen)
        }

        fun addAllLoginScreens() = apply {
            addLoginScreen(LoginScreen.PHONE_PASSWORD_SCREEN)
            addLoginScreen(LoginScreen.USERNAME_VALIDATION_SCREEN)
            addLoginScreen(LoginScreen.VERIFICATION_CODE_SCREEN)
            addLoginScreen(LoginScreen.FORGET_PASSWORD_SCREEN)
            addLoginScreen(LoginScreen.SIGN_UP_SCREEN)
            addLoginScreen(LoginScreen.CREATE_PASSWORD_SCREEN)
        }

        fun passwordLogin(passwordLogin: PasswordLogin) = apply {
            addLoginScreen(LoginScreen.PHONE_PASSWORD_SCREEN)
            this.passwordLogin = passwordLogin
        }

        fun forgetPassword(forgetPasswordConfig: ForgetPasswordConfig) = apply {
            addLoginScreen(LoginScreen.FORGET_PASSWORD_SCREEN)
            this.forgetPasswordConfig = forgetPasswordConfig
        }

        fun usernameValidation(verificationProvider: VerificationProvider) = apply {
            addLoginScreen(LoginScreen.USERNAME_VALIDATION_SCREEN)
            this.verificationProvider = verificationProvider
        }

        fun createPassword(config: CreatePasswordConfig) = apply {
            addLoginScreen(LoginScreen.CREATE_PASSWORD_SCREEN)
            this.createPasswordConfig = config
        }

        fun signUp() = apply {
            addLoginScreen(LoginScreen.SIGN_UP_SCREEN)
        }

        fun verificationLogin(verificationConfig: VerificationConfig) = apply {
            addLoginScreen(LoginScreen.VERIFICATION_CODE_SCREEN)
            this.verificationConfig = verificationConfig
        }

        fun build(): LoginConfig {
            val loginConfig = LoginConfig(this)
            loginConfig.loginScenarios.forEach { loginScreen ->
                val factory = loginConfig.loginScreenFactories[loginScreen]
                when (factory) {
                    PasswordLoginViewModelFactory -> {
                        loginConfig.initPasswordLogin(checkNotNull(passwordLogin) { "To initialize password login, you must provide the passwordLogin" })
                    }
                    ForgetPasswordViewModelFactory -> {
                        loginConfig.initForgetPassword(checkNotNull(forgetPasswordConfig) { "To initialize forget password, you must provide the forgetPasswordConfig" })
                    }
                    UsernameValidationViewModelFactory -> {
                        loginConfig.initUsernameValidation(checkNotNull(verificationProvider) { "To initialize username validation, you must provide the verificationProvider" })
                    }
                    SignUpViewModelFactory -> {
//                        { "To initialize sign up , you must provide the verificationProvider" }
                        loginConfig.initSignUp()
                    }
                    CreatePasswordViewModelFactory -> {
                        loginConfig.initCreatePassword(checkNotNull(createPasswordConfig) { "To initialize create password, you must provide the createPasswordConfig" })
                    }
                    VerificationLoginViewModelFactory -> {
                        loginConfig.initVerificationLogin(checkNotNull(verificationConfig) { "To initialize verification login, you must provide the verificationConfig" })
                    }
                }
                factory?.injectInputValidationProvider(inputValidationProvider)
            }
            return loginConfig
        }
    }


}