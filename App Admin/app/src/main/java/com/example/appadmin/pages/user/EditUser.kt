package com.example.appadmin.pages.user

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.UserController


class EditUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        val intent = intent

        val userId = intent.getStringExtra("user_id")!!.toInt()

        val userViewModel = ViewModelProvider(this)[UserController::class.java]
        userViewModel.getUser(userId).observe(this) {
            findViewById<ImageView>(R.id.editUserAvatar).setImageResource(R.drawable.profile)
            findViewById<TextView>(R.id.editUserName).text = it.getName()
            findViewById<TextView>(R.id.editUserEmail).text = it.getEmail()
            findViewById<TextView>(R.id.editUserGender).text = it.getGender()
            findViewById<TextView>(R.id.editUserDob).text = it.getDob()
            findViewById<TextView>(R.id.editUserPhone).text = it.getPhone()
            findViewById<TextView>(R.id.editUserAddress).text = it.getAddress()

            val isDisable = findViewById<Button>(R.id.editUser_DisableBtn)
            if (it.getIsDisable() == 0) {
                isDisable.text = "Khóa"
            } else {
                isDisable.text = "Mở"
            }
        }

        findViewById<Button>(R.id.editUser_EditBtn).setOnClickListener {

        }
        findViewById<Button>(R.id.editUser_DeleteBtn).setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("Bạn có muốn xóa tài khoản này không?")
            builder.setCancelable(true)

            builder.setPositiveButton(
                "Không",
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

            builder.setNegativeButton(
                "Có",
                DialogInterface.OnClickListener { dialog, id ->
                    val viewModel = ViewModelProvider(this)[UserController::class.java]
                    viewModel.deleteUser(userId).observe(this) {
                        val intent = Intent(this, Users::class.java)

                        startActivity(intent)
                    }
                })

            val alert: AlertDialog = builder.create()
            alert.show()
        }
    }
}

