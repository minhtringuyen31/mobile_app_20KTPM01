package com.example.myapplication.modals

class EditInfoRequest {
   // {  name,email,gender,date_of_birth,address }
    private lateinit var name:String
    private lateinit var email:String
    private lateinit var gender:String
    private lateinit var date_of_birth:String
    private lateinit var address:String

    constructor( name:String,email:String,gender:String,date_of_birth:String,address:String ){
        this.name=name
        this.email=email
        this.gender=gender
        this.date_of_birth=date_of_birth
        this.address=address
    }

    fun getName():String{
        return this.name
    }
    override fun toString():String{
        return "EditInfoRequest(name='$name',email='$email',gender='$gender',date_of_birth='$date_of_birth',address='$address')"
    }

}