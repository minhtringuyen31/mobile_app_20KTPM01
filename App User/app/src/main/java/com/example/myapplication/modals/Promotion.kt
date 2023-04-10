package com.example.myapplication.modals

class Promotion( private var id:Int,
                 private var name:String,
                 private  var description:String,
                 private var discount:Double,
                 private var image:String,
                 private var start_date:String,
                 private var end_date:String,
                 private var is_disable:Int,){
    fun setID(id:Int){
        this.id=id;
    }
    fun setName(name:String){
        this.name=name;
    }
    fun getDisable():Int{
        return this.id;
    }
    fun setDisable(status:Int){
        this.is_disable=status;
    }
    fun setDescription(description:String)
    {
        this.description=description;
    }
    fun setDisCount(discount:Double){
        this.discount=discount;
    }
    fun setBeginDay(begin:String){
        this.start_date=begin;
    }
    fun setEndDay(end:String){
        this.end_date=end;
    }
    fun setImage(image:String){
        this.image=image;
    }
    fun getID():Int
    {
        return this.id;
    }
    fun getName():String{
        return this.name;
    }
    fun getImage():String{
        return  this.image;
    }
    fun getBeginDay():String{
        return this.start_date;
    }
    fun getEndDay():String{
        return this.end_date;
    }
    fun getDiscount():Double{
        return this.discount;
    }

    override fun toString(): String {
        return "Promotion(id=$id, name='$name', description='$description', discount=$discount, image='$image', start_date='$start_date', end_date='$end_date', is_disable=$is_disable)"
    }

}