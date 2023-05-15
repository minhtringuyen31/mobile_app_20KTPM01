package com.example.myapplication.modals


class LoginRequest {
    private  var email:String ?= null;
    private  var password:String?=null;
    private  var token:String?=null;
    private  var userId:Int?=null;
    private  var role:Int?=null;
    private var status:Int ?=null;

    constructor(email: String, password: String, status: Int) {
        this.email = email
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
        return "LoginRequest(email=$email, password=$password, token=$token, userID=$userId, role=$role, status=$status)"
    }

}