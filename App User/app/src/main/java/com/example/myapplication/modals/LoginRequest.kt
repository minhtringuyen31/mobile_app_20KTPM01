package com.example.myapplication.modals


class LoginRequest {
    private  var phone:String ?= null;
    private  var password:String?=null;
    private  var token:String?=null;
    private  var userID:Int=-1;
    private  var role:Int=0;
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
        return "LoginRequest(phone=$phone, password=$password, token=$token, userID=$userID, role=$role, status=$status)"
    }


}