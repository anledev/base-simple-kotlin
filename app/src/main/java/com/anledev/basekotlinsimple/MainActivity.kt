package com.anledev.basekotlinsimple

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.anledev.basekotlinsimple.data.UserPreferences
import com.anledev.basekotlinsimple.ui.auth.AuthActivity
import com.anledev.basekotlinsimple.ui.home.HomeActivity
import com.anledev.basekotlinsimple.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {
//            Toast.makeText(this, it ?: "Token is Null", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, AuthActivity::class.java))
            val activity = if(it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }
}