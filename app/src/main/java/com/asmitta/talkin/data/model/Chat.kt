package com.asmitta.talkin.data.model

import com.asmitta.talkin.data.source.firebase.MessageLiveData
import com.asmitta.talkin.data.source.firebase.ReceiverLiveData
import com.google.firebase.firestore.Exclude

data class Chat(
    var chatId: String? = null,
    var senderId: String? = null,
    var receiverId: String? = null,
    var isGroupChat: Boolean? = null
) {
    @get:Exclude
    var user: ReceiverLiveData? = null

    @get:Exclude
    var message: MessageLiveData? = null
}