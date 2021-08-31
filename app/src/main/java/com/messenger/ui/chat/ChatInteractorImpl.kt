package com.messenger.ui.chat

import android.content.Context
import com.messenger.data.local.AppPreferences
import com.messenger.data.remote.repository.ConversationRepositoryImpl
import com.messenger.data.remote.request.MessageRequestObject
import com.messenger.service.MessengerApiService
import io.reactivex.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChatInteractorImpl(context: Context): ChatInteractor {

    private val preferences: AppPreferences = AppPreferences.create(context)
    private val service: MessengerApiService = MessengerApiService.getInstance()
    private val conversationRepository = ConversationRepositoryImpl(context)

    override fun sendMessage(
        recipientId: Long,
        message: String,
        listener: ChatInteractor.OnMessageSendFinishedListener
    ) {
        service.createMessage(MessageRequestObject(recipientId,message), preferences.accessToken as String)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread() as Scheduler)
            .subscribe( { _ ->
                listener.onSendSuccess()
            },
                {error ->
                    listener.onSendError()
                    error.printStackTrace()
                })
    }

    override fun loadMessages(
        conversationId: Long,
        listener: ChatInteractor.OnMessageLoadFinishedListener
    ) {
        conversationRepository.findConversationById(conversationId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread() as Scheduler)
            .subscribe({ res ->
                listener.onLoadSuccess(res)
            },
                { error ->
                    listener.onLoadError()
                    error.printStackTrace()
                })
    }


}