package com.example.appadmin.pages.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.R
import com.example.appadmin.controllers.UserController

class Users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val rvUser = findViewById<RecyclerView>(R.id.accountRV)
//        rvUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        rvUser.layoutManager = llm

        val userViewModel = ViewModelProvider(this)[UserController::class.java]
        userViewModel.getAllUser().observe(this) {
            val users = it

            val adapter = UserAdapter(this, users)

            rvUser.adapter = adapter
        }
    }
}