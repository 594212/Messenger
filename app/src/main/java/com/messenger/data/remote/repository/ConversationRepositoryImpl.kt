package com.messenger.data.remote.repository

import android.content.Context
import com.messenger.data.local.AppPreferences
import com.messenger.data.vo.ConversationListVO
import com.messenger.data.vo.ConversationVO
import com.messenger.service.MessengerApiService
import io.reactivex.Observable

class ConversationRepositoryImpl(ctx: Context): ConversationRepository {

    private val preferences: AppPreferences = AppPreferences.create(ctx)
    private val service : MessengerApiService = MessengerApiService.getInstance()

    override fun findConversationById(id: Long): Observable<ConversationVO> {
        return service.showConversation(preferences.accessToken as String,id)
    }

    override fun all(id: Long): Observable<ConversationListVO> {
        return service.listConversation(preferences.accessToken as String)
    }

}