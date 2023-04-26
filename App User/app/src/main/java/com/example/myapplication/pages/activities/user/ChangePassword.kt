package com.example.myapplication.pages.activities.user

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.modals.ChangePassRequest
import com.example.myapplication.utils.Status
import com.example.myapplication.viewmodels.ChangePassViewModel


class ChangePassword : AppCompatActivity() {
    private lateinit var changepass_newpass:EditText
    private lateinit var changepass_confirm:EditText
    private lateinit var button_changepass:Button
    private lateinit var changePassViewModel: ChangePassViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        changepass_newpass=findViewById(R.id.changepass_newpass)
        changepass_confirm=findViewById(R.id.changepass_confirm)
        button_changepass=findViewById(R.id.button_changepass)

        button_changepass.setOnClickListener {
            val changePassRequest= ChangePassRequest(changepass_newpass.text.toString(),changepass_confirm.text.toString())
            changePassViewModel= ViewModelProvider(this)[ChangePassViewModel::class.java]
            val sharedPreferences: SharedPreferences =getSharedPreferences("my_preference_name", MODE_PRIVATE)
            val userID = sharedPreferences.getString("userID", "")
            if (userID != null) {
                changePassViewModel.ChangePass(userID.toInt(),changePassRequest)
            } // phai tim duoc id cua nguoi dung dang dang nhap
            changePassViewModel.changePass.observe(this, Observer{
                it?.let { resource ->
                    when (resource.status) {

                        Status.SUCCESS -> {
                            Toast.makeText(this,"Cập nhật thành công!", Toast.LENGTH_LONG).show()
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

    }
}