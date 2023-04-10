package com.example.myapplication.modals

class OrderProduct {
    private var id:Int=0;
    private var order_id:Int=0;
    private lateinit var note:String
    private lateinit var shipping_address:String
    private var price:Double=0.0
    private var product_id:Int=0;
    private var quantity:Int=0;
    override fun toString(): String {
        return "OrderProduct(id=$id, order_id=$order_id, note='$note', shipping_address='$shipping_address', price=$price, product_id=$product_id, quantity=$quantity)"
    }

}