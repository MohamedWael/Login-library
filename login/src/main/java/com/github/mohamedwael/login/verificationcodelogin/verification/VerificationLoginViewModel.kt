package com.github.mohamedwael.login.verificationcodelogin.verification

import android.os.Handler
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.github.mohamedwael.login.R
import com.github.mohamedwael.login.base.BaseLoginViewModel
import com.github.mohamedwael.login.config.InputValidationProvider
import kotlin.math.absoluteValue

const val TIMER_TICK_DOWN_INTERVAL = 1000L
const val KEY_USER_NAME = "validated_username"
class VerificationLoginViewModel(
    private val verificationConfig: VerificationConfig,
    inputValidationProvider: InputValidationProvider
) : BaseLoginViewModel(inputValidationProvider) {
    var username: String? = null
    private var timerHandler: Handler? = null
    val resendButtonVisibility = ObservableBoolean(verificationConfig.isSendFirst)
    val isTimerClickable = ObservableBoolean(verificationConfig.onTimerClick != null)
    val pinCodeMaxLength = ObservableInt(verificationConfig.pinCodeMaxLength)
    val verificationTitle = ObservableInt(R.string.please_enter_the_code_to_verify_the_account)
    var remainingTime = verificationConfig.countDownTimeSecond.absoluteValue
    val verificationCounterText = MutableLiveData("0")
    val pinCodeListener = PinEntryEditText.OnPinEnteredListener {
        hideKeyboard()
        verificationConfig.onPinCodeChangeListener(username, it.toString())
    }
    var onSendClick: (() -> Unit)? = {
        resendButtonVisibility.set(false)
        verificationConfig.onSendClick?.invoke()
        startTimer()
    }
    var onTimerClick: (() -> Unit)? = verificationConfig.onTimerClick

    fun startTimer() {
        if (timerHandler == null && !resendButtonVisibility.get()) {
            timerHandler = Handler()
            verificationConfig.onTimerStarted?.invoke()
        }
        timerHandler?.postDelayed(::onTimerTickDown, TIMER_TICK_DOWN_INTERVAL)
    }

    private fun onTimerTickDown() {
        resendButtonVisibility.set(remainingTime <= 0)
        if (remainingTime > 0) {
            remainingTime -= 1
            verificationCounterText.value = remainingTime.toString()
            startTimer()
        } else {
            remainingTime = verificationConfig.countDownTimeSecond
            verificationConfig.onTimerTickDownFinish?.invoke()
            clearHandler()
        }
    }

    private fun clearHandler() {
        timerHandler?.removeCallbacks(::onTimerTickDown)
        timerHandler = null
    }

    override fun onCleared() {
        super.onCleared()
        clearHandler()
        onSendClick = null
        onTimerClick = null
        username = null
    }
}

object VerificationBindingAdapter {
    @JvmStatic
    @BindingAdapter("app:updateTimer")
    fun updateTimer(btn: Button, time: MutableLiveData<String>) {
        btn.text = try {
            val timer = time.value?.toInt() ?: 0
            if (timer < 10)
                "${btn.context.getString(R.string.remaining_time)}${"0" + time.value}"
            else "${btn.context.getString(R.string.remaining_time)}${time.value}"
        } catch (e: Exception) {
            "${btn.context.getString(R.string.remaining_time)}${time.value}"
        }
    }
}