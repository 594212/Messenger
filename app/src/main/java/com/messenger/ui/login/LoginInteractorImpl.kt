package com.messenger.ui.login

import com.messenger.data.local.AppPreferences
import com.messenger.data.remote.request.LoginRequestObject
import com.messenger.data.vo.UserVO
import com.messenger.service.MessengerApiService
import com.messenger.ui.auth.AuthInteractor
import io.reactivex.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginInteractorImpl: LoginInteractor {
    override lateinit var userDetails: UserVO
    override lateinit var accessToken: String
    override lateinit var submittedUsername: String
    override lateinit var submittedPassword: String

    private val service: MessengerApiService = MessengerApiService.getInstance()

    override fun login(
        username: String,
        password: String,
        listener: AuthInteractor.onAuthFinishedListener
    ) {
        when {
            username.isBlank() -> listener.onUsernameError()
            password.isBlank() -> listener.onPasswordError()
            else -> {
                submittedUsername = username
                submittedPassword = password
                val requestObject = LoginRequestObject(username, password)
                service.login(requestObject)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread() as Scheduler)
                    .subscribe({
                        res ->
                        if (res.code() != 403) {
                            accessToken = res.headers()["Authorization"] as String
                            listener.onAuthSuccess()
                        } else {
                            listener.onAuthError()
                        }
                    }, { error ->
                        listener.onAuthError()
                        error.printStackTrace()
                    })
            }
        }
        TODO("Not yet implemented")
    }

    override fun retrieveDetails(
        preferences: AppPreferences,
        listener: LoginInteractor.OnDetailsRetrievalFinishedListener
    ) {
        service.echoDetails(preferences.accessToken as String)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread() as Scheduler)
            .subscribe({ res ->
                userDetails = res
                listener.onDetailsRetrievalSuccess()
            }, { error ->
                listener.onDetailsRetrievalError()
                error.printStackTrace()
            })
    }

    override fun persistAccessToken(preferences: AppPreferences) {
        preferences.storeAccessToken(accessToken)
    }

    override fun persistUserDetails(preferences: AppPreferences) {
        preferences.storeUserDetails(userDetails)
    }

}
