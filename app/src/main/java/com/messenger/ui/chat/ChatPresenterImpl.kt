package com.messenger.ui.chat

import android.widget.Toast
import com.messenger.data.vo.ConversationVO
import com.messenger.utils.message.Message
import java.text.SimpleDateFormat

class ChatPresenterImpl(val view: ChatView): ChatPresenter,
    ChatInteractor.OnMessageLoadFinishedListener, ChatInteractor.OnMessageSendFinishedListener {
    private val interactor: ChatInteractor = ChatInteractorImpl(view.getContext())
    override fun onLoadSuccess(conversationVO: ConversationVO) {
        val adapter = view.getMessageListAdapter()
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        conversationVO.messages.forEach { message ->
            adapter.addToStart(Message(message.senderId, message.body, dateFormatter.parse(message.createdAt.split(".")[0])), true)
        }
    }

    override fun onLoadError() {
        view.showConversationLoadError()
    }

    override fun onSendSuccess() {
        Toast.makeText(view.getContext(),"Message sent", Toast.LENGTH_LONG).show()
    }

    override fun onSendError() {
        view.showMessageSendError()
    }

    override fun sendMessage(recipientId: Long, message: String) {
        interactor.sendMessage(recipientId, message, this)
    }

    override fun loadMessages(conversationId: Long) {
        interactor.loadMessages(conversationId, this)
    }
}