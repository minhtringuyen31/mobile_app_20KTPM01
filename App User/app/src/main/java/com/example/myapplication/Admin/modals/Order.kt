package com.example.myapplication.Admin.modals


class Order(
    private var id: Int? = null,
    private var user_id: Int? = null,
    private var order_date: String? = null,
    private var shipping_address: String? = null,
    private var total: Double? = null,
    private var status: Int? = null,
    private var promotion_id: Int? = null,
    private var payment_method_id: Int? = null
) {
    // Getters of all
    fun getId(): Int? {
        return id
    }

    fun getUserId(): Int? {
        return user_id
    }

    fun getOrderDate(): String? {
        return order_date
    }

    fun getShippingAddress(): String? {
        return shipping_address
    }

    fun getTotal(): Double? {
        return total
    }

    fun getStatus(): Int? {
        return status
    }

    fun getPromotionId(): Int? {
        return promotion_id
    }


    fun getPaymentMethodId(): Int? {
        return payment_method_id
    }

    // Setters of all
    fun setId(id: Int?) {
        this.id = id
    }

    fun setUserId(user_id: Int?) {
        this.user_id = user_id
    }

    fun setOrderDate(order_date: String?) {
        this.order_date = order_date
    }

    fun setShippingAddress(shipping_address: String?) {
        this.shipping_address = shipping_address
    }

    fun setTotal(total: Double?) {
        this.total = total
    }

    fun setStatus(status: Int?) {
        this.status = status
    }

    fun setPromotionId(promotion_id: Int?) {
        this.promotion_id = promotion_id
    }

    fun setPaymentMethodId(payment_method_id: Int?) {
        this.payment_method_id = payment_method_id
    }

    // toString
    override fun toString(): String {
        return "Order(id=$id, user_id=$user_id, order_date=$order_date, shipping_address=$shipping_address, total=$total, status=$status, promotion_id=$promotion_id, payment_method_id=$payment_method_id)"
    }

}