package com.example.myapplication.modals

class SignupRequest {
    private lateinit var phone:String
    private lateinit var password:String
    private lateinit var confirmpass:String

    constructor(phone:String,pass:String,confirmpass:String){
        this.phone=phone
        this.password=pass
        this.confirmpass=confirmpass
    }
    fun getPhone():String{
        return phone
    }
    override fun toString(): String {
        return "SignupRequest(phone='$phone', password='$password', confirmpass='$confirmpass')"
    }
}