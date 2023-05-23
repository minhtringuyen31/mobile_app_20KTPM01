package com.example.myapplication.pages.activities.user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.modals.ForgotPassRequest
import com.example.myapplication.utils.Status
import com.example.myapplication.viewmodels.user.ForgotPasswordViewModel
import com.example.myapplication.viewmodels.user.UserViewModel

class ForgotPassword_Step2 : AppCompatActivity() {
    private lateinit var back_btn: AppCompatImageView
    private lateinit var btn_vertify: Button
    private lateinit var fg_otp: EditText
    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_step2)
        back_btn = findViewById(R.id.back_btn1)
        fg_otp = findViewById(R.id.fg_otp)
        btn_vertify = findViewById(R.id.button_verify)

        back_btn.setOnClickListener {
            val intent = Intent(
                this,
                // Signup::class.java
                ForgotPassword_Step1::class.java
            )
            startActivity(intent)
        }
        val email = intent.getStringExtra("email")

        btn_vertify.setOnClickListener {
            if (email != null) {
                val forgotRequest = ForgotPassRequest(email, fg_otp.text.toString())
                forgotPasswordViewModel =
                    ViewModelProvider(this)[ForgotPasswordViewModel::class.java]

                forgotPasswordViewModel.CheckOTP(forgotRequest)
                forgotPasswordViewModel.checkotp.observe(this, Observer {
                    it?.let { resource ->
                        when (resource.status) {
// make more
                            Status.SUCCESS -> {
                                Toast.makeText(this, "Xác thực thành công!", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(
                                    this,
//                                ForgotPassword_Step2::class.java
                                    Login::class.java
                                )
                                startActivity(intent)
                            }

                            Status.ERROR -> {
                                if (fg_otp.text.toString() == "") {
                                    Toast.makeText(
                                        this,
                                        "Không được để trống mã xác thực",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {
                                    Toast.makeText(
                                        this,
                                        "Mã xác thực không đúng.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                            Status.LOADING -> {
                                Toast.makeText(this, "Đang tiến hành", Toast.LENGTH_SHORT).show()
                            }
                            else -> {

                            }
                        }

                    }
                })

            }
        }
    }
}