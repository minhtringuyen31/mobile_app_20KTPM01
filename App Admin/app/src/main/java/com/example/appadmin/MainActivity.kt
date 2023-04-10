package com.example.appadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.appadmin.controllers.UserController
import com.example.appadmin.pages.dashboard.Dashboard
import com.example.appadmin.databinding.ActivityMainBinding
import com.example.appadmin.modals.User
import com.example.appadmin.utils.Utils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userController = UserController()
        val items = userController.getAllUser()
        println(items)
//        if (items != null) {
//            for (i in items) {
//                i.printUser()
//            }
//        }

        binding.loginButton.setOnClickListener {
            if (binding.username.text.toString() == "user" && binding.password.text.toString() == "1234") {
                val intent = Intent(this, Dashboard::class.java)

                startActivity(intent)
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}