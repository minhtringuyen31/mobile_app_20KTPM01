package com.example.myapplication.Admin.pages.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.UserController
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

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