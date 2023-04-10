package com.example.myapplication.modals

class CartItem {
    private var id:Int=0;
    private var user_id:Int=0
    private var cart_id:Int=0
    private var product_id:Int=0
    private var quantity:Int=0
    private lateinit var size:String
    private var price:Double =0.0
    override fun toString(): String {
        return "CartItem(id=$id, user_id=$user_id, cart_id=$cart_id, product_id=$product_id, quantity=$quantity, size='$size', price=$price)"
    }

}