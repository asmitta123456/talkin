package com.asmitta.talkin.view.userProfile

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnAttach
import androidx.databinding.DataBindingUtil
import com.asmitta.talkin.R
import com.asmitta.talkin.databinding.ActivityMainBinding
import com.asmitta.talkin.view.FirebaseViewModel
import com.asmitta.talkin.view.NavigationManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navigationManager by lazy { NavigationManager(this, binding) }
    private val firebaseVm: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        // binding.root.doOnAttach { navigationManager.run { } }
        setContentView(R.layout.activity_profile)
        val tvEmail = findViewById<TextView>(R.id.email_id)
        val tvUser = findViewById<TextView>(R.id.user_id)
        firebaseVm.user.observe(this,{
            it.first.let {
                if (it != null) {
                    tvEmail.text = "Username -: " + it.username
                    tvUser.text = "User ID -: " + it.userId
                }
            }
        })

    }
}