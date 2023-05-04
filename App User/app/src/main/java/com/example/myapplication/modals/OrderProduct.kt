package com.example.myapplication.modals

class OrderProduct(
    private var order_id:Int,
    private var note:String,
    private var price:Double,
    private var product_id:Int,
    private var quantity:Int,
) {
    private var id:Int = 0;

    override fun toString(): String {
        return "OrderProduct(id=$id, order_id=$order_id, note='$note', price=$price, product_id=$product_id, quantity=$quantity)"
    }

}