package com.example.appadmin.pages.user

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.UserController
import com.example.appadmin.modals.User


class EditUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        val password = findViewById<EditText>(R.id.editUserPassword)
        val confirmPassword =
            findViewById<EditText>(R.id.editUserConfirmPassword)
        val name = findViewById<EditText>(R.id.editUserName)
        val gender = findViewById<EditText>(R.id.editUserGender)
        val email = findViewById<EditText>(R.id.editUserEmail)
        val phone = findViewById<EditText>(R.id.editUserPhone)
        val dob = findViewById<EditText>(R.id.editUserDob)
        val address = findViewById<EditText>(R.id.editUserAddress)
        val avatar = findViewById<ImageView>(R.id.editUserAvatar)

        val intent = intent

        val userId = intent.getStringExtra("user_id")!!.toInt()

        findViewById<Button>(R.id.editUser_cancelBtn).setOnClickListener {
            val intent = Intent(this, Users::class.java)

            startActivity(intent)
        }


        val userViewModel = ViewModelProvider(this)[UserController::class.java]
        userViewModel.getUser(userId).observe(this) {
            avatar.setImageResource(R.drawable.profile)
            name.hint = it.getName()
            email.hint = it.getEmail()
            gender.hint = it.getGender()
            dob.hint = it.getDob()
            password.hint = it.getPassword()
            confirmPassword.hint = it.getPassword()
            phone.hint = it.getPhone()
            address.hint = it.getAddress()
        }

        findViewById<Button>(R.id.editUser_saveBtn).setOnClickListener {

            if (password == confirmPassword) {
                val userViewModel = ViewModelProvider(this)[UserController::class.java]
                val newUser = User(
                    1,
                    name.text.toString(),
                    gender.text.toString(),
                    email.text.toString(),
                    phone.text.toString(),
                    password.text.toString(),
                    dob.text.toString(),
                    address.text.toString(),
                    avatar.resources.toString(),
                    "user",
                    0
                )
                userViewModel.updateUser(userId, newUser).observe(this) {
                    val intent = Intent(this, Users::class.java)
                    startActivity(intent)
                }
            } else {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setMessage("Mật khẩu không đồng nhất!!!")
                builder.setCancelable(true)

                builder.setPositiveButton("Tiếp tục",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

                builder.setNegativeButton(
                    "Thoát",
                    DialogInterface.OnClickListener { dialog, id ->
                        val intent = Intent(this, Users::class.java)

                        startActivity(intent)
                    })

                val alert: AlertDialog = builder.create()
                alert.show()
            }
        }
    }
}

