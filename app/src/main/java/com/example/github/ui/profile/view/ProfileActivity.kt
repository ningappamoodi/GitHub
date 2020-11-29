package com.example.github.ui.profile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.github.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileSearchFragment.newInstance())
                    .commitNow()
        }
    }
}