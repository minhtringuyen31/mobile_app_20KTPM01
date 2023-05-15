package com.example.myapplication.Admin.pages.user

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
import com.example.myapplication.Admin.controllers.UserController
import com.example.myapplication.Admin.modals.User
import com.example.myapplication.R
class AddUser : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        findViewById<Button>(R.id.addUser_saveBtn).setOnClickListener {

            val password = findViewById<EditText>(R.id.addUserPassword).text.toString()
            val confirmPassword =
                findViewById<EditText>(R.id.addUserConfirmPassword).text.toString()
            val name = findViewById<EditText>(R.id.addUserName).text.toString()
            val gender = findViewById<EditText>(R.id.addUserGender).text.toString()
            val email = findViewById<EditText>(R.id.addUserEmail).text.toString()
            val phone = findViewById<EditText>(R.id.addUserPhone).text.toString()
            val dob = findViewById<EditText>(R.id.addUserDob).text.toString()
            val address = findViewById<EditText>(R.id.addUserAddress).text.toString()
            val avatar = findViewById<ImageView>(R.id.addUserAvatar).resources.toString()

            if (password == confirmPassword) {
                val userViewModel = ViewModelProvider(this)[UserController::class.java]
                val newUser = User(
                    1, name, gender, email, phone, password, dob, address, avatar, "user", 0
                )
                userViewModel.createUser(newUser).observe(this) {
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

                builder.setNegativeButton("Thoát", DialogInterface.OnClickListener { dialog, id ->
                    val intent = Intent(this, Users::class.java)

                    startActivity(intent)
                })

                val alert: AlertDialog = builder.create()
                alert.show()
            }
        }

        findViewById<Button>(R.id.addUser_cancelBtn).setOnClickListener {
            val intent = Intent(this, Users::class.java)

            startActivity(intent)
        }
    }
}