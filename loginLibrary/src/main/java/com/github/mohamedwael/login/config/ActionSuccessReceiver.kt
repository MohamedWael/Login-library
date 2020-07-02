package com.github.mohamedwael.login.config

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle

const val BROADCAST_ACTION_TYPE_LOGIN = "com.github.mohamedwael.login"
const val BROADCAST_ACTION_TYPE_SIGN_UP = "com.github.mohamedwael.signup"
const val BROADCAST_ACTION_TYPE = "com.github.mohamedwael.login.type"
const val BROADCAST_ACTION_SUCCESS = "com.github.mohamedwael.login.success"
const val BROADCAST_SUCCESS_ACTION_DATA = "com.github.mohamedwael.login.success.data"

open class ActionSuccessReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        onSuccess(context, intent?.extras)
    }

    open fun onSuccess(context: Context?, response: Bundle?) {}

    fun getPasswordLoginIntentFilter() = IntentFilter(BROADCAST_ACTION_SUCCESS)
}