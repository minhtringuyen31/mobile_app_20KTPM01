package com.example.myapplication.modals

class SignupRequest {
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var confirmpass:String

    constructor(email:String,pass:String,confirmpass:String){
        this.email=email
        this.password=pass
        this.confirmpass=confirmpass
    }
    fun getEmail():String{
        return email
    }
    override fun toString(): String {
        return "SignupRequest(phone='$email', password='$password', confirmpass='$confirmpass')"
    }
}