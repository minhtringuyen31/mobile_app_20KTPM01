package com.example.myapplication.modals

class ChangePassRequest {
    private lateinit var newpassword:String
    private lateinit var confirmpass:String

    constructor(pass:String, confirmpass:String){
        this.newpassword=pass
        this.confirmpass=confirmpass
    }

    fun getNewPass():String{
        return this.newpassword
    }

    override fun toString(): String {
        return "ChangePassRequest(newpassword='$newpassword', confirmpass='$confirmpass')"
    }

}