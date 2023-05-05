package com.example.myapplication.modals

class CartItem(
    private var user_id: Int,
    private var cart_id: Int,
    private var product_id: Int,
    private var quantity: Int,
    private var size: String,
    private var price: Double,
    private var topping: String,
    private var name: String,
    private  var description: String,
    private  var image: String,
    private var category_id: Int,
    private var notes: String,
) {
    private var id:Int=0
    // Setter methods

    fun setUserId(userId:Int) {
        this.user_id = userId
    }

    fun setCartId(cartId:Int) {
        this.cart_id = cartId
    }
    fun getNotes():String
    {
        return this.notes
    }
    fun setNote(notes:String){
        this.notes=notes;
    }

    fun setProductId(productId:Int) {
        this.product_id = productId
    }

    fun setQuantity(quantity:Int) {
        this.quantity = quantity
    }

    fun setSize(size:String) {
        this.size = size
    }

    fun setPrice(price:Double) {
        this.price = price
    }

    fun setTopping(topping:String) {
        this.topping = topping
    }

    fun setName(name:String) {
        this.name = name
    }

    fun setDescription(description:String) {
        this.description = description
    }

    fun setImage(image:String) {
        this.image = image
    }

    fun setCategoryId(categoryId:Int) {
        this.category_id = categoryId
    }

    // Getter methods
    fun getId():Int {
        return this.id
    }

    fun getUserId():Int {
        return this.user_id
    }

    fun getCartId():Int {
        return this.cart_id
    }

    fun getProductId():Int {
        return this.product_id
    }

    fun getQuantity():Int {
        return this.quantity
    }

    fun getSize():String {
        return this.size
    }

    fun getPrice():Double {
        return this.price
    }

    fun getTopping():String {
        return this.topping
    }

    fun getName():String {
        return this.name
    }

    fun getDescription():String {
        return this.description
    }

    fun getImage():String {
        return this.image
    }

    fun getCategoryId():Int {
        return this.category_id
    }

    override fun toString(): String {
        return "CartItem(user_id=$user_id, cart_id=$cart_id, product_id=$product_id, quantity=$quantity, size='$size', price=$price, topping='$topping', name='$name', description='$description', image='$image', category_id=$category_id, notes='$notes', id=$id)"
    }

    init {
        this.id = id
    }



}