package com.messenger.data.remote.repository

import com.messenger.data.vo.ConversationListVO
import com.messenger.data.vo.ConversationVO
import io.reactivex.Observable

interface ConversationRepository {
    fun findConversationById( id : Long): Observable<ConversationVO>
    fun all(): Observable<ConversationListVO>

}