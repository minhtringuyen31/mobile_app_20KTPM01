package com.example.myapplication.Admin.modals


class OrderProduct(
    private var id: Int? = null,
    private var note: String? = null,
    private var order_id: Int? = null,
    private var product_id: Int? = null,
    private var quantity: Int? = null,
    private var price: Double? = null,
    private var topping:String?= null,
) {
    // Getters of all
    fun getId(): Int? {
        return id
    }

    fun getNote(): String? {
        return note
    }
    fun setTopping(topping:String){
        this.topping = topping
    }
    fun getTopping():String?{
        return this.topping;
    }

    fun getOrderId(): Int? {
        return order_id
    }

    fun getProductId(): Int? {
        return product_id
    }

    fun getQuantity(): Int? {
        return quantity
    }

    fun getPrice(): Double? {
        return price
    }

    // Setters of all
    fun setId(id: Int?) {
        this.id = id
    }

    fun setNote(note: String?) {
        this.note = note
    }

    fun setOrderId(order_id: Int?) {
        this.order_id = order_id
    }

    fun setProductId(product_id: Int?) {
        this.product_id = product_id
    }

    fun setQuantity(quantity: Int?) {
        this.quantity = quantity
    }

    fun setPrice(price: Double?) {
        this.price = price
    }
}