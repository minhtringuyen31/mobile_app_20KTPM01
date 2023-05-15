package com.example.myapplication.modals

class User(
    private var name: String,
    private var gender: String,
    private var email: String,
    private var phone: String,
    private var password: String?,
    private var date_of_birth: String,
    private var address: String,
    private var avatar: String,
    private var role: String,// admin =1, user=0
    private var is_disable:Int )
{


    private  var  id: Int =0;
    fun getId():Int{
        return id
    }
    fun getPhone():String{
        return phone
    }
    fun getName():String{
        return name
    }
    fun setName(name:String){
        this.name=name
    }

    fun getEmail():String {
        return email;
    }
    fun setEmail(email:String){
        this.email=email
    }
    fun getGender():String{
        return gender
    }
    fun getDOB():String{
        return date_of_birth
    }
    fun setDOB(dob:String){
        this.date_of_birth=dob
    }
    fun getAddress():String{
        return address
    }
    fun setAddress(address:String){
        this.address=address
    }
    fun getPassword(): String? {
        return password
    }
    fun setPassword(password: String){
        this.password=password;
    }
//    constructor( name:String,email:String,gender:String,dob:String,address:String) : this() {
//        this.name=name;
//        this.email=email;
//        this.gender=gender;
//        this.date_of_birth=dob;
//        this.address=address;
//    }

    override fun toString(): String {
        return "User(id=$id, name='$name', gender='$gender', email='$email', phone='$phone', password='$password', date_of_birth='$date_of_birth', address='$address', avatar='$avatar', role='$role', is_disable=$is_disable)"
    }

    fun getAvatar(): String {
        return avatar
    }

    fun getRole(): String {
        return role
    }


}
