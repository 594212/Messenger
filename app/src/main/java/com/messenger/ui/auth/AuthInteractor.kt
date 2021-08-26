package com.messenger.ui.auth

import com.messenger.data.local.AppPreferences
import com.messenger.data.vo.UserVO

interface AuthInteractor {

    var userDetails: UserVO
    var accessToken: String
    var submittedUsername: String
    var submittedPassword: String
    interface onAuthFinishedListener {
        fun onAuthSuccess()
        fun onAuthError()
        fun onUsernameError()
        fun onPasswordError()
    }
    fun persistAccessToken(preferences: AppPreferences)
    fun persistUserDetails(preferences: AppPreferences)
}