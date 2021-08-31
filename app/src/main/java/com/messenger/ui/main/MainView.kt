package com.messenger.ui.main

import com.messenger.ui.BaseView
import com.messenger.ui.main.fragments.ContactsFragment
import com.messenger.ui.main.fragments.ConversationsFragment

interface MainView: BaseView {
    fun showConversationLoadError()
    fun showContactsLoadError()
    fun showConversationScreen()
    fun showContactsScreen()
    fun getContactsFragment(): ContactsFragment
    fun getConversationsFragment(): ConversationsFragment
    fun showNoConversations()
    fun navigateToLogin()
    fun navigateToSettings()
}