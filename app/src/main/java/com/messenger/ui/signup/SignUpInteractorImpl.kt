package com.messenger.ui.signup

import com.messenger.data.local.AppPreferences
import com.messenger.data.remote.request.LoginRequestObject
import com.messenger.data.remote.request.UserRequestObject
import com.messenger.data.vo.UserVO
import com.messenger.service.MessengerApiService
import com.messenger.ui.auth.AuthInteractor
import io.reactivex.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpInteractorImpl: SignUpInteractor {

    override lateinit var userDetails: UserVO
    override lateinit var accessToken: String
    override lateinit var submittedUsername: String
    override lateinit var submittedPassword: String
    private val service = MessengerApiService.getInstance()

    override fun signUp(
        username: String,
        phoneNumber: String,
        password: String,
        listener: SignUpInteractor.OnSignUpFinishedListener
    ) {
        when  {
            username.isBlank() -> listener.onUsernameError()
            password.isBlank() -> listener.onPasswordError()
            phoneNumber.isBlank() -> listener.onPhoneNumberError()
            else -> {
                submittedUsername = username
                submittedPassword = password
                val  userRequestObject = UserRequestObject(username, password, phoneNumber)
                service.createUser(userRequestObject)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread() as Scheduler)
                    .subscribe({ res -> userDetails = res
                    listener.onSuccess()},
                               { error -> listener.onError()
                    error.printStackTrace() })
            }
        }
    }

    override fun getAuthorization(listener: AuthInteractor.onAuthFinishedListener) {
        val userRequestObject = LoginRequestObject(submittedUsername, submittedPassword)
        service.login(userRequestObject)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread() as Scheduler)
            .subscribe({ res ->
                accessToken = res.headers()["Authorization"] as String
                listener.onAuthSuccess()
            }, { error ->
                listener.onAuthError()
                error.printStackTrace()
            })

    }

    override fun persistAccessToken(preferences: AppPreferences) {
        preferences.storeUserDetails(userDetails)
    }

    override fun persistUserDetails(preferences: AppPreferences) {
        preferences.storeAccessToken(accessToken)
    }

}