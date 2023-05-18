package com.example.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_home)

        val name = intent.getStringExtra(SingIn.KEY)

        val welcomeName = findViewById<TextView>(R.id.UserWelcomeName)

        welcomeName.text = "$name"
    }
}