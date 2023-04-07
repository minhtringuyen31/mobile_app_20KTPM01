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
    private var is_disable: Boolean? = null
) {
    fun printUser() {
        println(id.toString() + " " + name + " " + gender + " " + email + " " + phone + " " + password + " " + date_of_birth + " " + address + " " + avatar + " " + role + " " + is_disable)
    }
}