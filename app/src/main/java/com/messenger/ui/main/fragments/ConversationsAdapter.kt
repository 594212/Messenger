package com.messenger.ui.main.fragments

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.messenger.R
import com.messenger.data.local.AppPreferences
import com.messenger.data.vo.ConversationVO
import com.messenger.ui.chat.ChatActivity
import com.messenger.ui.chat.ChatView

class ConversationsAdapter(private val context: Context, private val dataSet: List<ConversationVO>):
    RecyclerView.Adapter<ConversationsAdapter.ViewHolder>(), ChatView.ChatAdapter{
    val preferences: AppPreferences = AppPreferences.create(context)

    class ViewHolder(val itemLayout: LinearLayout): RecyclerView.ViewHolder(itemLayout)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_conversations,null,false)
            .findViewById<LinearLayout>(R.id.ll_container)
        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        val itemLayout = holder.itemLayout
        itemLayout.findViewById<TextView>(R.id.tv_username).text = item.secondPartyUsername
        itemLayout.findViewById<TextView>(R.id.tv_preview).text = item.messages[item.messages.size-1].body

        itemLayout.setOnClickListener {
            val message = item.messages[0]
            val recipientId: Long = if (message.senderId == preferences.userDetails.id) {
                message.recipientId
            } else {
                message.senderId
            }
            navigateToChat(item.secondPartyUsername, recipientId, item.conversationId)
        }

    }
    override fun navigateToChat(recipientName: String, recipientId: Long, conversationId: Long?) {
        val intent = Intent(context, ChatActivity::class.java)
        intent.putExtra("CONVERSATION_ID", conversationId)
        intent.putExtra("RECIPIENT_ID", recipientId)
        intent.putExtra("RECIPIENT_NAME", recipientName)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int = dataSet.size

}