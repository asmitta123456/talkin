package com.asmitta.talkin.data.repository

import androidx.lifecycle.LiveData
import com.asmitta.talkin.data.model.User
import com.asmitta.talkin.data.source.firebase.ChatRoomListLiveData
import com.asmitta.talkin.data.source.firebase.ChatRoomLiveData
import com.asmitta.talkin.utils.states.AuthenticationState
import com.asmitta.talkin.utils.states.NetworkState

interface Repository {

    fun getChatRoomListLiveData(): ChatRoomListLiveData

    fun getChatRoomLiveData(): ChatRoomLiveData

    fun getUserLiveData(): LiveData<Pair<User?, AuthenticationState>>

    suspend fun isUsernameAvailable(
        username: String,
        callBack: (networkState: NetworkState) -> Unit
    )

    suspend fun searchForUser(
        username: String,
        callBack: (networkState: NetworkState, userList: MutableList<User>) -> Unit
    )

    fun addUsername(
        username: String,
        callBack: (usernameStatus: NetworkState) -> Unit
    )

    fun fetchConfigMsgLength(callBack: (msgLengh: Int) -> Unit)
}