package com.messenger.ui.main.fragments

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.messenger.R
import com.messenger.data.vo.UserVO
import com.messenger.ui.chat.ChatActivity
import com.messenger.ui.chat.ChatView

class ContactsAdapter( private val context: Context, private val dataSet: List<UserVO>):
                    RecyclerView.Adapter<ContactsAdapter.ViewHolder>(), ChatView.ChatAdapter {

    class ViewHolder(val itemLayout: LinearLayout): RecyclerView.ViewHolder(itemLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_contacts,parent,false)
        val llContainer = itemLayout.findViewById<LinearLayout>(R.id.ll_container)
        return ViewHolder(llContainer)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        val itemLayout = holder.itemLayout
        itemLayout.findViewById<TextView>(R.id.tv_username).text = item.username
        itemLayout.findViewById<TextView>(R.id.tv_phone).text = item.phoneNumber
        itemLayout.findViewById<TextView>(R.id.tv_status).text = item.status

        itemLayout.setOnClickListener {
            navigateToChat(item.username, item.id)
        }
    }

    override fun navigateToChat(recipientName: String, recipientId: Long, conversationId: Long?) {
        val intent = Intent(context, ChatActivity::class.java)
        intent.putExtra("RECIPIENT_ID", recipientId)
        intent.putExtra("RECIPIENT_NAME", recipientName)
        context.startActivity(intent)

    }

    override fun getItemCount(): Int = dataSet.size

}