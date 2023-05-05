package com.example.myapplication.modals

class OrderProduct(
    private var note:String,
    private var order_id:Int,
    private var product_id:Int,
    private var quantity:Int,
    private var price:Double,
    private var size:String,
    private var topping: String,
) {
    private var id:Int = 0;
    override fun toString(): String {
        return "OrderProduct(order_id=$order_id, note='$note', price=$price, product_id=$product_id, size='$size', topping='$topping', quantity=$quantity, id=$id)"
    }


}