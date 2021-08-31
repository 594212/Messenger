package com.messenger.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.messenger.R
import com.messenger.data.vo.ConversationVO
import com.messenger.ui.main.MainActivity
import com.messenger.ui.main.fragments.ConversationsAdapter

class ConversationsFragment: Fragment(), View.OnClickListener {

    private lateinit var activity: MainActivity
    private lateinit var rvConversations: RecyclerView
    private lateinit var fabContacts: FloatingActionButton
    var conversations: ArrayList<ConversationVO> = ArrayList()
    lateinit var conversationAdapter: ConversationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val baseLayout = inflater.inflate(R.layout.fragment_conversations, container, false)

        rvConversations = baseLayout.findViewById(R.id.rv_conversations)
        fabContacts = baseLayout.findViewById(R.id.fab_contacts)
        conversationAdapter = ConversationsAdapter(requireContext(), conversations)

        rvConversations.adapter = conversationAdapter
        rvConversations.layoutManager = LinearLayoutManager(getActivity()?.baseContext)
        fabContacts.setOnClickListener(this)
        return baseLayout
    }

    override fun onClick(view: View) {
        if(view.id == R.id.fab_contacts) {
            this.activity.showContactsScreen()
        }
    }

    fun setActivity(activity: MainActivity) {
        this.activity = activity
    }

}