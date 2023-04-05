package com.example.myapplication.modals

import androidx.appcompat.app.AppCompatActivity

class User:AppCompatActivity{
    private var  id: Int =0;
    private var name: String ="Default"
    private var gender: String="Default"
    private var email: String="Default"
    private var phone: String="Default"
    private var password: String="Default"
    private var date_of_birth: String="Default"
    private var address: String="Default"
    private var avatar: String="Default"
    private var role: String="Default"
    private var is_disable: Boolean=false

    constructor(
        name: String,
        gender: String,
        email: String,
        phone: String,
        password: String,
        date_of_birth: String,
        address: String,
        avatar: String,
        role: String,
        is_disable: Boolean
    ) {
        this.name = name
        this.gender = gender
        this.email = email
        this.phone = phone
        this.password = password
        this.date_of_birth = date_of_birth
        this.address = address
        this.avatar = avatar
        this.role = role
        this.is_disable = is_disable
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', gender='$gender', email='$email', phone='$phone', password='$password', date_of_birth='$date_of_birth', address='$address', avatar='$avatar', role='$role', is_disable=$is_disable)"
    }


}
