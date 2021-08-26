package com.messenger.data.remote.repository

import com.messenger.data.vo.UserListVO
import com.messenger.data.vo.UserVO
import io.reactivex.Observable

interface UserRepository {
    fun findById(id: Long): Observable<UserVO>
    fun all(): Observable<UserListVO>
    fun echoDetails(): Observable<UserVO>
}