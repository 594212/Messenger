package com.messenger.ui.login

import com.messenger.data.local.AppPreferences
import com.messenger.ui.auth.AuthInteractor

interface LoginInteractor: AuthInteractor {
    interface OnDetailsRetrievalFinishedListener {
        fun onDetailsRetrievalSuccess()
        fun onDetailsRetrievalError()
    }

    fun login(username: String, password: String,
    listener: AuthInteractor.onAuthFinishedListener)
    fun retrieveDetails(preferences: AppPreferences,
    listener: OnDetailsRetrievalFinishedListener)
}