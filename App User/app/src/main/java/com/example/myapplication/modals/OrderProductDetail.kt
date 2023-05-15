package com.example.myapplication.modals

class OrderProductDetail(
    private var note:String,
    private var order_id:Int,
    private var product_id:Int,
    private var quantity:Int,
    private var price:Double,
    private var size:String,
    private var topping: String,
    private var name: String,
    private var image: String,
) {
    private var id: Int = 0;

    fun getNote(): String{
        return note
    }

    fun getOrderId():Int{
        return order_id
    }
    fun getProductId(): Int{
        return product_id
    }
    fun getQuantity():Int{
        return quantity
    }
    fun getPrice():Double{
        return price
    }
    fun getSize(): String{
        return size
    }
    fun getTopping(): String{
        return topping
    }
    fun getProductName(): String{
        return name
    }
    fun getProductImg(): String{
        return image
    }

    override fun toString(): String {
        return "OrderProductDetail(note='$note', order_id=$order_id, product_id=$product_id, quantity=$quantity, price=$price, size='$size', topping='$topping', name='$name', image='$image', id=$id)"
    }


}