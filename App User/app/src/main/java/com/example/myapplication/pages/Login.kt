package com.example.myapplication.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class Login : AppCompatActivity() {
    private lateinit var  buttonLogin:Button
    private lateinit var routeSignUp:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonLogin = findViewById(R.id.button_login)
        routeSignUp = findViewById(R.id.route_signup)
        buttonLogin.setOnClickListener {
            val intent = Intent(
                this,
                Homepage::class.java
            )
            startActivity(intent)
        }
        routeSignUp.setOnClickListener {
            val intent = Intent(
                this,
                Signup::class.java
            )
            startActivity(intent)
        }

    }
}