package com.asmitta.talkin.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.asmitta.talkin.ui.util.ViewModelFactory
import com.asmitta.talkin.ui.util.launchFragmentInHiltContainer
import com.asmitta.talkin.util.mock
import com.asmitta.talkin.utils.FileHelper
import com.asmitta.talkin.utils.states.NetworkState
import com.asmitta.talkin.view.FirebaseViewModel
import com.asmitta.talkin.view.chatRoom.ChatFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ChatFragmentTest {

    companion object {
        val viewModel: FirebaseViewModel = mock()
        val fileHelperMock: FileHelper = mock()
    }

    private val pushImgStatus = MutableLiveData<NetworkState>()
    private val pushAudioStatus = MutableLiveData<NetworkState>()
    private lateinit var appContext: Context

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        Mockito.reset(viewModel)
        appContext = InstrumentationRegistry.getInstrumentation().context
        `when`(viewModel.pushImgStatus).thenReturn(pushImgStatus)
        `when`(viewModel.pushAudioStatus).thenReturn(pushAudioStatus)
        launchFragmentInHiltContainer<ChatFragmentTest>()
    }

    @Test
    fun onPhotoPickerClick() {
    }

    @Test
    fun onSendButtonTouch() {
    }

    @Test
    fun onSendButtonClick() {
    }

    @Test
    fun pushMsg() {
    }

    @Test
    fun pushImg() {
    }

    @Test
    fun pushAudio() {
    }

    class ChatFragmentTest : ChatFragment() {
        override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
            return ViewModelFactory(viewModel)
        }
    }
}