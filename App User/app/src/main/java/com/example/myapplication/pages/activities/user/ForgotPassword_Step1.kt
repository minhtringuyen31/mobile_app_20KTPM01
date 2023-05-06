package com.example.myapplication.pages.activities.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword_Step1 : AppCompatActivity() {
    private lateinit var fg_email:EditText
    private lateinit var button_sendemail:Button
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_step1)
        fg_email=findViewById(R.id.fg_email)
        button_sendemail=findViewById(R.id.button_sendemail)

        auth=FirebaseAuth.getInstance()
        button_sendemail.setOnClickListener {
            val vertify=fg_email.text.toString()
            auth.sendPasswordResetEmail(vertify)
                .addOnCanceledListener {
                    Toast.makeText(this, "please check your Email!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, it.toString(),Toast.LENGTH_LONG).show()
                }

            val intent = Intent(
                this,
                Login::class.java
            )
            startActivity(intent)


        }
    }
}