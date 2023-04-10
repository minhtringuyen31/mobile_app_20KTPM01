package com.example.myapplication.modals

import androidx.appcompat.app.AppCompatActivity

class User(
    private var name: String,
    private var gender: String,
    private var email: String,
    private var phone: String,
    private var password: String,
    private var date_of_birth: String,
    private var address: String,
    private var avatar: String,
    private var role: String,
    private var is_disable: Int
) : AppCompatActivity() {
    private var  id: Int =0;

    override fun toString(): String {
        return "User(id=$id, name='$name', gender='$gender', email='$email', phone='$phone', password='$password', date_of_birth='$date_of_birth', address='$address', avatar='$avatar', role='$role', is_disable=$is_disable)"
    }


}
