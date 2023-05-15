package com.example.myapplication.modals


class LoginRequest {
    private  var phone:String ?= null;
    private  var password:String?=null;
    private  var token:String?=null;
    private  var userId:Int?=null;
    private  var role:Int?=null;
    private var status:Int ?=null;

    constructor(phone: String, password: String, status: Int) {
        this.phone = phone
        this.password = password
        this.status = status
    }
    fun getStatusUser(): Int? {
        return this.status;
    }
    fun getRole():Int?{
        return this.role;
    }
    fun getUserID(): Int? {
        return this.userId;
    }
    override fun toString(): String {
        return "LoginRequest(phone=$phone, password=$password, token=$token, userID=$userId, role=$role, status=$status)"
    }

}