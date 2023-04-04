package com.example.myapplication.modals

import java.io.Serializable
data class Product(
    var name: String,
    var price: String,
    var image: Int,
    var description: String
):Serializable