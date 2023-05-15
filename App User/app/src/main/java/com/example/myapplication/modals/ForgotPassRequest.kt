package com.example.myapplication.modals

class ForgotPassRequest {
    private lateinit var email:String
    private lateinit var otp:String

    constructor(email:String,otp:String){
        this.email=email
        this.otp=otp
    }
    fun getEmail():String{
        return this.email
    }
    fun getOTP():String{
        return this.otp
    }
    fun setOTP(otp:String){
        this.otp=otp
    }

    override fun toString(): String {
        return "ForgotPassRequest(email='$email', otp='$otp')"
    }
}