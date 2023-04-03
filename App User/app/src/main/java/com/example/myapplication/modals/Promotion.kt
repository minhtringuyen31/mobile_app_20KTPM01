package com.example.myapplication.modals

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity


class Promotion :AppCompatActivity{
    private var id:String ="";
     private var name:String="";
    private  var description:String="";
    private var discount:Double? = null;
     private var image:String="";
     private var beginDay:String="";
    private var endDay:String="";

    constructor(
        id: String,
        name: String,
        description: String,
        discount: Double?,
        image: String,
        beginDay: String,
        endDay: String
    ) {
        this.id = id
        this.name = name
        this.description = description
        this.discount = discount
        this.image = image
        this.beginDay = beginDay
        this.endDay = endDay
    }

    fun setID(id:String){
        this.id=id;
    }
    fun setName(name:String){
        this.name=name;
    }
    fun setDescription(description:String)
    {
        this.description=description;
    }
    fun setDisCount(discount:Double){
        this.discount=discount;
    }
    fun setBeginDay(begin:String){
        this.beginDay=begin;
    }
    fun setEndDay(end:String){
        this.endDay=end;
    }
    fun setImage(image:String){
        this.image=image;
    }
    fun getID():String
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
        return this.beginDay;
    }
    fun getEndDay():String{
        return this.endDay;
    }
    fun getDiscount():Double{
        return this.discount!!;
    }
    fun getDecodeImage(): Bitmap? {
        try {
            val assetManager =  MainActivity.appContext.resources.assets

            val inputStream = assetManager.open(this.getImage())

            val bitmap = BitmapFactory.decodeStream(inputStream)
            return bitmap;
        } catch (e: java.lang.Exception) {
            // handler
            e.printStackTrace()
        } finally {
            // optional finally block
        }

        return null;
    }



}