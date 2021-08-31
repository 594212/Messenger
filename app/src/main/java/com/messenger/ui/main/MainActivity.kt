package com.messenger.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.messenger.R
import com.messenger.data.local.AppPreferences
import com.messenger.data.vo.ConversationVO
import com.messenger.ui.login.LoginActivity
import com.messenger.ui.main.fragments.ContactsFragment
import com.messenger.ui.main.fragments.ConversationsFragment
import com.messenger.ui.settings.SettingsActivity
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var llContainer: LinearLayout
    private lateinit var presenter: MainPresenter

    private val contactFragment = ContactsFragment()
    private val conversationFragment = ConversationsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenterImpl(this)
        conversationFragment.setActivity(this)
        contactFragment.setActivity(this)
        bingViews()
        showConversationScreen()
    }

    override fun bingViews() {
        llContainer = findViewById(R.id.ll_container)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun showConversationLoadError() {
        Toast.makeText(this, "Unable to load conversations. Try again later", Toast.LENGTH_LONG).show()
    }

    override fun showContactsLoadError() {
        Toast.makeText(this, "Unable to load contacts. Try again later.", Toast.LENGTH_LONG).show()
    }

    override fun showConversationScreen() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.ll_container,conversationFragment)
        fragmentTransaction.commit()
        presenter.loadConversations()
        supportActionBar?.title = "Messenger"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showContactsScreen() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.ll_container, contactFragment)
        fragmentTransaction.commit()
        presenter.loadContacts()
        supportActionBar?.title = "Contacts"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun showNoConversations() {
        Toast.makeText(this, "You have no active conversations.", Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            android.R.id.home -> showConversationScreen()
            R.id.action_settings -> navigateToSettings()
            R.id.action_logout -> presenter.executeLogout()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getContext(): Context = this

    override fun getContactsFragment(): ContactsFragment = contactFragment

    override fun getConversationsFragment(): ConversationsFragment = conversationFragment

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun navigateToSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}