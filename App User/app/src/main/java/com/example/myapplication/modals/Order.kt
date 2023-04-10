package com.example.myapplication.modals

class Order {
    private var id:Int=0;
    private var user_id:Int=0;
    private lateinit var order_date:String
    private lateinit var shipping_address:String
    private var total:Double=0.0
    private var status:Boolean = false;
    private var promotion_id:Int=0;
    private var payment_method_id:Int=0;
    override fun toString(): String {
        return "Order(id=$id, user_id=$user_id, order_date='$order_date', shipping_address='$shipping_address', total=$total, status=$status, promotion_id=$promotion_id, payment_method_id=$payment_method_id)"
    }


}