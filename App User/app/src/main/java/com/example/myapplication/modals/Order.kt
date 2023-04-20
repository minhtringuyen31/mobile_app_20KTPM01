package com.example.myapplication.modals

class Order(
    private var id:Int,
    private var user_id:Int,
    private var order_date:String,
    private var shipping_address:String,
    private var total:Double,
    private var status:Int,
    private var promotion_id:Int,
    private var payment_method_id:Int,
) {

    // Getter for id
    fun getId(): Int {
        return id
    }

    // Getter for user_id
    fun getUserId(): Int {
        return user_id
    }

    // Getter for order_date
    fun getOrderDate(): String {
        return order_date
    }

    // Getter for shipping_address
    fun getShippingAddress(): String {
        return shipping_address
    }

    // Getter for total
    fun getTotal(): Double {
        return total
    }

    // Getter for status
    fun getStatus(): Int {
        return status
    }

    // Getter for promotion_id
    fun getPromotionId(): Int {
        return promotion_id
    }

    // Getter for payment_method_id
    fun getPaymentMethodId(): Int {
        return payment_method_id
    }

    override fun toString(): String {
        return "Order(id=$id, user_id=$user_id, order_date='$order_date', shipping_address='$shipping_address', total=$total, status=$status, promotion_id=$promotion_id, payment_method_id=$payment_method_id)"
    }

}