package com.github.mohamedwael.login.verificationcodelogin.verification

data class VerificationConfig(
    val isSendFirst: Boolean = false,
    val pinCodeMaxLength: Int = 4,
    val countDownTimeSecond: Int = 60,
    val onPinCodeChangeListener: (username: String?, pinCode: String) -> Unit,
    val onSendClick: ((username: String) -> Unit)? = null,
    val onTimerClick: (() -> Unit)? = null,
    val onTimerTickDownFinish: (() -> Unit)? = null,
    val onTimerStarted: (() -> Unit)? = null
)