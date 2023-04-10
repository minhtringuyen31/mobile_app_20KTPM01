package com.example.appadmin.modals

class User(
    private var id: Int? = null,
    private var name: String? = null,
    private var gender: String? = null,
    private var email: String? = null,
    private var phone: String? = null,
    private var password: String? = null,
    private var date_of_birth: String? = null,
    private var address: String? = null,
    private var avatar: String? = null,
    private var role: String? = null,
    private var is_disable: Int? = null
) {
//    fun printUser() {
//        println(id.toString() + " " + name + " " + gender + " " + email + " " + phone + " " + password + " " + date_of_birth + " " + address + " " + avatar + " " + role + " " + is_disable)
//    }

    override fun toString(): String {
        return "User(id=$id, name=$name, gender=$gender, email=$email, phone=$phone, password=$password, date_of_birth=$date_of_birth, address=$address, avatar=$avatar, role=$role, is_disable=$is_disable)"
    }

    fun getId(): Int? {
        return this.id
    }

    fun getName(): String? {
        return this.name
    }

    fun getGender(): String? {
        return this.gender
    }

    fun getEmail(): String? {
        return this.email
    }

    fun getPhone(): String? {
        return this.phone
    }

    fun getDob(): String? {
        return this.date_of_birth
    }

    fun getAddress(): String? {
        return this.address
    }

    fun getAvatar(): String? {
        return this.avatar
    }

    fun getRole(): String? {
        return this.role
    }

    fun getIsDisable(): Int? {
        return this.is_disable
    }
}