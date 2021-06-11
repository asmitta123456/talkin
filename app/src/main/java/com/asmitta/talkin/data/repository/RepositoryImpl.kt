package com.asmitta.talkin.data.repository

import androidx.lifecycle.LiveData
import com.asmitta.talkin.data.model.User
import com.asmitta.talkin.data.source.firebase.ChatRoomListLiveData
import com.asmitta.talkin.data.source.firebase.ChatRoomLiveData
import com.asmitta.talkin.data.source.firebase.FirebaseDaoImpl
import com.asmitta.talkin.utils.states.AuthenticationState
import com.asmitta.talkin.utils.states.NetworkState
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val firebaseDaoImpl: FirebaseDaoImpl,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    override fun getChatRoomListLiveData(): ChatRoomListLiveData {
        return firebaseDaoImpl.chatRoomListLiveData
    }

    override fun getChatRoomLiveData(): ChatRoomLiveData {
        return firebaseDaoImpl.chatRoomLiveData
    }

    override fun getUserLiveData(): LiveData<Pair<User?, AuthenticationState>> {
        return firebaseDaoImpl.userLiveData
    }

    override suspend fun isUsernameAvailable(
        username: String,
        callBack: (networkState: NetworkState) -> Unit
    ) {
        firebaseDaoImpl.isUsernameAvailable(username, callBack)
    }

    override suspend fun searchForUser(
        username: String,
        callBack: (networkState: NetworkState, userList: MutableList<User>) -> Unit
    ) {
        firebaseDaoImpl.searchForUser(username, callBack)
    }

    override fun addUsername(
        username: String,
        callBack: (usernameStatus: NetworkState) -> Unit
    ) {
        firebaseDaoImpl.addUsername(username, callBack)
    }

    override fun fetchConfigMsgLength(callBack: (msgLengh: Int) -> Unit) {
        firebaseDaoImpl.fetchConfigMsgLength(callBack)
    }
}