package com.example.myapplication.modals

import kotlin.properties.Delegates

class LoginRequest {
    private lateinit var phone:String;
    private lateinit var password:String;
    private var status =0;

    constructor(phone: String, password: String, status: Int) {
        this.phone = phone
        this.password = password
        this.status = status
    }
    fun getStatusUser():Int{
        return this.status;
    }

    override fun toString(): String {
        return "LoginRequest(phone='$phone', password='$password', status=$status)"
    }


}