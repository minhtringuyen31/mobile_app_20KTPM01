package com.example.appadmin.pages.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.R
import com.example.appadmin.controllers.UserController
import com.example.appadmin.pages.dashboard.Dashboard

class Users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val rvUser = findViewById<RecyclerView>(R.id.accountRV)
        findViewById<Button>(R.id.addUser).setOnClickListener {
            val intent = Intent(this, AddUser::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.backUserBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        rvUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val userViewModel = ViewModelProvider(this)[UserController::class.java]
        userViewModel.getAllUser().observe(this) {
            val users = it

            val adapter = UserAdapter(this, users)

            println("Hi")

            rvUser.adapter = adapter
        }
    }
}