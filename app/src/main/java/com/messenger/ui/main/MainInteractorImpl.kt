package com.messenger.ui.main

import android.content.Context
import com.messenger.data.local.AppPreferences
import com.messenger.data.remote.repository.ConversationRepositoryImpl
import com.messenger.data.remote.repository.UserRepositoryImpl
import com.messenger.service.MessengerApiService
import io.reactivex.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainInteractorImpl( val context: Context): MainInteractor {

    private val userRepository = UserRepositoryImpl(context)
    private val conversationRepository = ConversationRepositoryImpl(context)
    private val preferences = AppPreferences.create(context)

    override fun loadContacts(listener: MainInteractor.OnContactLoadFinishedListener) {
        userRepository.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread() as Scheduler)
            .subscribe({ res -> listener.onContactsLoadSuccess(res) }, { error ->
                listener.onContactsLoadError()
                error.printStackTrace()
            } )
    }

    override fun loadConversation(listener: MainInteractor.OnConversationsLoadFinishedListener) {
        conversationRepository.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread() as Scheduler)
            .subscribe({ res ->
                listener.onConversationLoadSuccess(res)
            }, {
                error ->
                listener.onConversationLoadError()
                error.printStackTrace()
            })
    }

    override fun logout(listener: MainInteractor.OnLogoutFinishedListener) {
        val preferences = AppPreferences.create(context)
        preferences.clear()
        listener.onLogoutSuccess()
    }
}