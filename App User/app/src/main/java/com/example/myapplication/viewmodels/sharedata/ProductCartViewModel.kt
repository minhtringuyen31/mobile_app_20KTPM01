package com.example.myapplication.viewmodels.sharedata

import androidx.lifecycle.ViewModel

class ProductCartViewModel:ViewModel() {
    private var id:Int=0;
    private lateinit var name:String
    private lateinit var image:String
    private lateinit var description:String
    private var priceL:Double=0.0
    private var priceM:Double=0.0
    private var priceS:Double=0.0
    private  var topping:String="EMPTY"
    private lateinit var note:String
    private  var price:Double=0.0
    private var category_id:Int=0
    private var quantity=0;
    private var nameFragment:String="homepage";
    fun getId(): Int {
        return id
    }
    fun setNameFragment(name:String){
        this.nameFragment= name;
    }
    fun getNameFragment():String{
        return this.nameFragment;
    }
    fun setQuantiTy(quantity:Int){
        this.quantity=quantity;
    }
    fun getQuantiTy():Int{
        return this.quantity;
    }
    fun setImage(image:String){
        this.image=image
    }
    fun getImage():String{
        return this.image
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getDescription(): String {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getPriceL(): Double {
        return priceL
    }

    fun setPriceL(priceL: Double) {
        this.priceL = priceL
    }

    fun getPriceM(): Double {
        return priceM
    }

    fun setPriceM(priceM: Double) {
        this.priceM = priceM
    }

    fun getPriceS(): Double {
        return priceS
    }

    fun setPriceS(priceS: Double) {
        this.priceS = priceS
    }

    fun getTopping(): String {
        return topping
    }

    fun setTopping(topping: String) {
        this.topping = topping
    }

    fun getNote(): String {
        return note
    }

    fun setNote(note: String) {
        this.note = note
    }

    fun getPrice(): Double {
        return price
    }

    fun setPrice(price: Double) {
        this.price = price
    }

    fun getCategoryId(): Int {
        return category_id
    }

    fun setCategoryId(category_id: Int) {
        this.category_id = category_id
    }
}