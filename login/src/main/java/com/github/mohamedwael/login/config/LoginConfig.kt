package com.github.mohamedwael.login.config

enum class LoginScenario {
    PHONE_PASSWORD_LOGIN, VERIFICATION_CODE_LOGIN, FORGET_PASSWORD, SIGN_UP
}

class LoginConfig private constructor(val builder:Builder){
    val loginScenarios = mutableSetOf<LoginScenario>()
    class Builder{
        fun verificationLogin(enable:Boolean){}
        fun passwordLogin(enable:Boolean){}
        fun forgetPassword(enable:Boolean){}
        fun signUp(enable:Boolean){}
    }
}