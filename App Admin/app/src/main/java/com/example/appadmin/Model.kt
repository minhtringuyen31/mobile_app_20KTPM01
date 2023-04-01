package com.example.appadmin

class DashboardItems(val name: String, val image: Int)

class Product(
    val id: String,
    var name: String,
    var description: String,
    var price: Double,
    var size: String,
    var price_S: Double,
    var price_M: Double,
    var price_L: Double,
    var note: String,
    var image: String,
    var status: Boolean,
    var category_id: String,
    var updatedAt: String,
    var releaseDate: String,
    var sales: Int
)

class Category(val id: String, var name: String)

class Account(
    val id: String,
    var fullname: String,
    var gender: String,
    var dob: String,
    var email: String,
    var phone: String,
    var address: String,
    var isDisable: Boolean,
    var avatar: String,
    var role: String
)

class Order()