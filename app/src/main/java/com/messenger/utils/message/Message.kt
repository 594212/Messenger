package com.messenger.utils.message

import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.IUser
import java.util.*

class Message(private val authorId: Long, private val body: String, private val createdAt: Date): IMessage {
    override fun getId(): String {
        return authorId.toString()
    }

    override fun getText(): String {
        return body
    }

    override fun getUser(): IUser {
        return Author(authorId, "")
    }

    override fun getCreatedAt(): Date {
        return createdAt
    }
}