package com.messenger.ui.chat

interface ChatPresenter {
    fun sendMessage(recipientId: Long, message: String)
    fun loadMessages(conversationId: Long)
}