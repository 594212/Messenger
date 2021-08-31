package com.messenger.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.messenger.R
import com.messenger.data.vo.UserVO
import com.messenger.ui.main.MainActivity

class ContactsFragment: Fragment() {
    private lateinit var activity: MainActivity
    private lateinit var rvContacts: RecyclerView
    var contacts: ArrayList<UserVO> = ArrayList()
    lateinit var contactsAdapter: ContactsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val baseLayout = inflater.inflate(R.layout.fragment_contacts, container, false)
        rvContacts = baseLayout.findViewById(R.id.rv_contacts)
        contactsAdapter = ContactsAdapter(getActivity() as Context, contacts)
        rvContacts.adapter = contactsAdapter
        rvContacts.layoutManager = LinearLayoutManager(getActivity()?.baseContext)
        return baseLayout
        arguments
    }

    fun setActivity(activity: MainActivity) {
        this.activity = activity
    }
}