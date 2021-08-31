package com.messenger.utils.message

import com.stfalcon.chatkit.commons.models.IUser

class Author(val id: Long, val username: String) : IUser {
    override fun getId(): String {
        return id.toString()
    }

    override fun getName(): String {
        return username
    }

    override fun getAvatar(): String? {
        return null
    }

}
