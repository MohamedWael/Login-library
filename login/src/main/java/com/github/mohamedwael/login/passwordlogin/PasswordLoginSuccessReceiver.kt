package com.github.mohamedwael.login.passwordlogin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import java.io.Serializable

abstract class PasswordLoginSuccessReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        onLoginSuccess(context, intent?.extras?.getSerializable(BROADCAST_DATA_LOGIN_SUCCESS))
    }

    abstract fun onLoginSuccess(context: Context?, response: Serializable?)

    fun getPasswordLoginIntentFilter() = IntentFilter(BROADCAST_ACTION_LOGIN_SUCCESS)
}