package com.example.appadmin.modals

class Order(
    private var id: Int? = null,
    private var user_id: Int? = null,
    private var order_date: String? = null,
    private var shipping_address: String? = null,
    private var total: Double? = null,
    private var status: Short? = null,
    private var promotion_id: Int? = null,
    private var payment_method_id: Int? = null
)