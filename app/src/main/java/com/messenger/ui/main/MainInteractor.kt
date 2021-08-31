package com.messenger.ui.main

import com.messenger.data.vo.ConversationListVO
import com.messenger.data.vo.UserListVO

interface MainInteractor {
    interface OnConversationsLoadFinishedListener {
        fun onConversationLoadSuccess(conversationListVO: ConversationListVO)
        fun onConversationLoadError()
    }

    interface OnContactLoadFinishedListener {
        fun onContactsLoadSuccess(userListVO: UserListVO)
        fun onContactsLoadError()
    }

    interface OnLogoutFinishedListener {
        fun onLogoutSuccess()
    }

    fun loadContacts(listener: MainInteractor.OnContactLoadFinishedListener)
    fun loadConversation(listener: MainInteractor.OnConversationsLoadFinishedListener)
    fun logout(listener:MainInteractor.OnLogoutFinishedListener)
}