package com.example.myapplication.Admin.pages.user

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.UserController
import com.example.myapplication.Admin.modals.User
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Users : AppCompatActivity() {
    private lateinit var userList: List<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val searchTypeList = listOf("Name", "Email", "Phone")

        val rvUser = findViewById<RecyclerView>(R.id.accountRV)
        val searchTypeUser = findViewById<Spinner>(R.id.searchTypeUser)
        val searchUser = findViewById<AutoCompleteTextView>(R.id.searchUser)
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
            userList = it
            val adapter = UserAdapter(this, users)

            rvUser.adapter = adapter
            val adapterACTV = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_single_choice,
                userList
            )
            searchUser.setAdapter(adapterACTV)
        }
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            searchTypeList
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            searchTypeUser.adapter = it
        }
        searchUser.addTextChangedListener {
            if (searchTypeUser.selectedItem.toString() == "Name") {
                val adapter = UserAdapter(
                    this,
                    userList.filter { user ->
                        user.getName().toString().contains(searchUser.text.toString(), true)
                    })
                rvUser.adapter = adapter
            } else if (searchTypeUser.selectedItem.toString() == "Email") {
                val adapter = UserAdapter(
                    this,
                    userList.filter { user ->
                        user.getEmail().toString().contains(searchUser.text.toString(), true)
                    })
                rvUser.adapter = adapter
            } else if (searchTypeUser.selectedItem.toString() == "Phone") {
                val adapter = UserAdapter(
                    this,
                    userList.filter { user ->
                        user.getPhone().toString().contains(searchUser.text.toString(), true)
                    })
                rvUser.adapter = adapter
            }
        }
    }
}