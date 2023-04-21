package com.example.myapplication.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.modals.LoginRequest
import com.example.myapplication.utils.Status
import com.example.myapplication.viewmodels.CategoryViewModel
import com.example.myapplication.viewmodels.LoginViewModel

class Login : AppCompatActivity() {
    private lateinit var buttonLogin: Button
    private lateinit var routeSignUp: TextView
    private lateinit var login_phone: EditText
    private lateinit var login_pass: EditText
    private lateinit var loginViewModel: LoginViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        buttonLogin = findViewById(R.id.button_login)
        routeSignUp = findViewById(R.id.route_signup)
        login_phone = findViewById(R.id.login_phone)
        login_pass = findViewById(R.id.login_pass)
        buttonLogin.setOnClickListener {
            val loginRequest =
                LoginRequest(login_phone.text.toString(), login_pass.text.toString(), 0);
            loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
            loginViewModel.loginUser(loginRequest);
            loginViewModel.statusLogin.observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {

                        Status.SUCCESS -> {
                            Toast.makeText(this,"Đăng nhập thành công!", Toast.LENGTH_LONG).show()
                            val intent = Intent(
                                this,
                                MainActivity::class.java
                            )
                            intent.putExtra("status","1")
                            startActivity(intent)

                        }
                        Status.ERROR -> {

                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            Toast.makeText(this, "Dang doc database", Toast.LENGTH_LONG).show()
                        }
                        else -> {

                        }
                    }
                }
            })

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