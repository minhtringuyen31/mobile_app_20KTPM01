package com.example.myapplication.pages.activities.user
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.modals.ForgotPassRequest
import com.example.myapplication.utils.Status
import androidx.lifecycle.Observer
import com.example.myapplication.viewmodels.user.ChangePassViewModel

import com.example.myapplication.viewmodels.user.ForgotPasswordViewModel

import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import android.util.Log
import com.example.myapplication.viewmodels.user.UserViewModel
import java.net.URL
import java.security.Security


class ForgotPassword_Step1 : AppCompatActivity() {
    private lateinit var fg_email: EditText
    private lateinit var button_sendemail: Button
    private lateinit var back_btn: AppCompatImageView
    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel
    private lateinit var userViewModel: UserViewModel

//    fun sendOtp(email: String) {
//        val url = URL("http://your-node-server/send-otp")
//        val connection = url.openConnection() as HttpURLConnection
//        connection.requestMethod = "POST"
//        connection.doOutput = true
//
//        val data = "email=$email"
//        val writer = OutputStreamWriter(connection.outputStream)
//        writer.write(data)
//        writer.flush()
//
//        val responseCode = connection.responseCode
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            // OTP sent successfully
//        } else {
//            // Error sending OTP
//        }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_step1)
        fg_email = findViewById(R.id.fg_email)
        button_sendemail = findViewById(R.id.button_sendemail)
        back_btn = findViewById(R.id.back_btn1)

        back_btn.setOnClickListener {
            val intent = Intent(
                this,
                Login::class.java
            )
            startActivity(intent)
        }

        button_sendemail.setOnClickListener {

            // call api
            val forgotRequest=ForgotPassRequest(fg_email.text.toString(),"1")
            forgotPasswordViewModel= ViewModelProvider(this)[ForgotPasswordViewModel::class.java]

            forgotPasswordViewModel.SetOTP(forgotRequest)
            forgotPasswordViewModel.setotp.observe(this,Observer{
                it?.let { resource ->
                    when (resource.status) {
// make more
                        Status.SUCCESS -> {
                            Toast.makeText(this,"OTP đã gửi đến email của bạn!", Toast.LENGTH_LONG).show()

//
//                            userViewModel=ViewModelProvider(this)[UserViewModel::class.java]
//                            //println(fg_email.text.toString())
//                            userViewModel.getUserbyEmail(fg_email.text.toString())
//                           // println(fg_email.text.toString())
//                            userViewModel.user.observe(this, Observer {
//
////                                    it?.let { resource ->
//                                        println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
////                                        when (resource.status) {
////
////                                            Status.SUCCESS -> {
//                                                println(fg_email.text.toString())
//                                                val sharedPreferences: SharedPreferences =getSharedPreferences("user", MODE_PRIVATE)
//                                                val userID = sharedPreferences.edit().putString("userID",it.getId().toString())
//
//                                                println("test"+userID)
////                                                //Toast.makeText(this,"Cập nhật thành công!", Toast.LENGTH_LONG).show()
////                                            }
////                                            Status.ERROR -> {
////
////                                                Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
////                                            }
////                                            Status.LOADING -> {
////                                                Toast.makeText(this, "Loading", Toast.LENGTH_LONG).show()
////                                            }
////                                            else -> {
////
////                                            }
////                                        }
////                                    }


//                            })

                            startActivity(Intent(this, ForgotPassword_Step2::class.java ).putExtra("email",fg_email.text.toString()))
                        }

                        Status.ERROR -> {
                            if(fg_email.text.toString()=="") {
                                Toast.makeText(
                                    this,
                                    "Không được để trống email",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                            else{
                                Toast.makeText(this, "Email không tồn tại.", Toast.LENGTH_LONG).show()

                            }
                        }
                        Status.LOADING -> {
                            Toast.makeText(this, "Đang gửi yêu cầu", Toast.LENGTH_LONG).show()
                        }
                        else -> {

                        }
                    }
                }

            })

       }

        }
    }
