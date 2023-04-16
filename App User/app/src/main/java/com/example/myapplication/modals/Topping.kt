package com.example.myapplication.modals

class Topping(
    private  var category_id: Int,
    private  var name: String,
    private var price: Double,
    private var checked: Int
) {

    fun getName():String{
        return this.name
    }
    fun getPrice():Double{
        return this.price
    }
    fun getChecked():Int{
        return this.checked;
    }
    fun setName(name:String){
         this.name = name
    }
    fun setPrice(price:Double){
         this.price=price
    }
    fun setChecked(checked:Int){
         this.checked=checked
    }
    fun getCategoryID():Int{
        return this.category_id
    }

    override fun toString(): String {
        return "Topping(category_id='$category_id', name='$name', price=$price, checked=$checked)"
    }

}