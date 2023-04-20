package com.example.appadmin.pages.user

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.UserController
import com.example.appadmin.modals.User

class UserDetail : AppCompatActivity() {
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val intent = intent

        val userId = intent.getStringExtra("user_id")!!.toInt()

        val userViewModel = ViewModelProvider(this)[UserController::class.java]
        userViewModel.getUser(userId).observe(this) {
            findViewById<ImageView>(R.id.detailUserAvatar).setImageResource(R.drawable.profile)
            findViewById<TextView>(R.id.detailUserName).text = it.getName()
            findViewById<TextView>(R.id.detailUserEmail).text = it.getEmail()
            findViewById<TextView>(R.id.detailUserGender).text = it.getGender()
            findViewById<TextView>(R.id.detailUserDob).text = it.getDob()
            findViewById<TextView>(R.id.detailUserPhone).text = it.getPhone()
            findViewById<TextView>(R.id.detailUserAddress).text = it.getAddress()

            user = it

            val isDisable = findViewById<Button>(R.id.detailUser_disableBtn)
            if (it.getIsDisable() == 0) {
                isDisable.text = "Khóa"
            } else {
                isDisable.text = "Mở"
            }
        }

        findViewById<Button>(R.id.detailUser_editBtn).setOnClickListener {
            val intent = Intent(this, EditUser::class.java)
            intent.putExtra("user_id", userId.toString())
            startActivity(intent)
        }
        findViewById<Button>(R.id.detailUser_cancelBtn).setOnClickListener {
            val intent = Intent(this, Users::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.detailUser_disableBtn).setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            val isDisable = findViewById<Button>(R.id.detailUser_disableBtn)

            builder.setMessage("Bạn có muốn thay đổi trạng thái tài khoản này không?")

            builder.setCancelable(true)

            builder.setPositiveButton(
                "Không"
            ) { dialog, id ->
                dialog.cancel()
            }

            builder.setNegativeButton(
                "Có"
            ) { dialog, id ->
                var setDisable = 0

                if (user.getIsDisable() == 0) {
                    userViewModel.disableUser(user.getId()!!).observe(this) {

                    }
                } else {
                    userViewModel.enableUser(user.getId()!!).observe(this) {

                    }
                }
                val intent = Intent(this, Users::class.java)
                startActivity(intent)
            }

            val alert: AlertDialog = builder.create()
            alert.show()
        }
    }
}