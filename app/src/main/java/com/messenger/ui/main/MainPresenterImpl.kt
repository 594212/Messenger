package com.messenger.ui.main

import android.content.Context
import com.messenger.data.vo.ConversationListVO
import com.messenger.data.vo.UserListVO

class MainPresenterImpl(val view: MainView): MainPresenter, MainInteractor.OnLogoutFinishedListener,
    MainInteractor.OnConversationsLoadFinishedListener, MainInteractor.OnContactLoadFinishedListener {
        val interactor = MainInteractorImpl(view.getContext())

    override fun onConversationLoadSuccess(conversationListVO: ConversationListVO) {
        if(conversationListVO.conversations.isEmpty()) {

            val conversationsFragment = view.getConversationsFragment()
            val conversations = conversationsFragment.conversations
            val adapter = conversationsFragment.conversationAdapter
            conversations.clear()
            adapter.notifyDataSetChanged()

            conversationListVO.conversations.forEach() { contact ->
                conversations.add(contact)
                adapter.notifyItemInserted(conversations.size-1)
            }
        } else {
            view.showNoConversations()
        }
    }

    override fun onConversationLoadError() {
        view.showConversationLoadError()
    }

    override fun onContactsLoadSuccess(userListVO: UserListVO) {

        val contactsFragment = view.getContactsFragment()
        val contacts = contactsFragment.contacts
        TODO("Not yet implemented")
    }

    override fun onContactsLoadError() {
        view.showContactsLoadError()
    }

    override fun onLogoutSuccess() {
        view.navigateToLogin()
    }

    override fun loadConversations() {
        interactor.loadConversation(this)
    }

    override fun loadContacts() {
        interactor.loadContacts(this)

    }

    override fun executeLogout() {
        interactor.logout(this)
    }


}