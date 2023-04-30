package com.example.myapplication.pages.activities.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.modals.SignupRequest
import com.example.myapplication.utils.Status
import com.example.myapplication.viewmodels.authen.SignupViewModel
class Signup : AppCompatActivity() {
    private lateinit var button_signup:Button
    private  lateinit var signup_phone:EditText
    private  lateinit var signup_pass:EditText
    private lateinit var signup_confirmpass:EditText
    private lateinit var route_login:TextView
    private lateinit var signupViewModel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        button_signup=findViewById(R.id.button_signup)
        signup_phone=findViewById(R.id.signup_phone)
        signup_pass=findViewById(R.id.signup_pass)
        signup_confirmpass=findViewById(R.id.signup_confirmpass)
        route_login=findViewById(R.id.route_login)

        button_signup.setOnClickListener {
            //pass after hash

            val signupRequest=SignupRequest(signup_phone.text.toString(),signup_pass.text.toString(),signup_confirmpass.text.toString())
            signupViewModel= ViewModelProvider(this)[SignupViewModel::class.java]
            signupViewModel.SignUp(signupRequest)
            signupViewModel.signup.observe(this, Observer{
                    it?.let { resource ->
                        when (resource.status) {

                            Status.SUCCESS -> {
                                Toast.makeText(this,"Sign up Success!", Toast.LENGTH_LONG).show()
                                val intent = Intent(
                                    this,
                                    Login::class.java
                                )
                                startActivity(intent)
                            }
                            Status.ERROR -> {

                                Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
                            }
                            Status.LOADING -> {
                                Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
                            }
                            else -> {

                            }
                        }
                    }

            })

        }
        route_login.setOnClickListener {
            val intent = Intent(
                this,
                Login::class.java
            )
            startActivity(intent)
        }
    }

}