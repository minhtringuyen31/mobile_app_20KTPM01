package com.example.myapplication.modals

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.myapplication.MainActivity

class Category(
    private var id: Int,
    private var name: String,
    private var image: String,
    private var is_disable: Int
) {

    fun setID(id:Int){
        this.id=id;
    }
    fun setName(name:String){
        this.name=name;
    }
    fun setPathImage(path:String){
        this.image=path;
    }

    fun getPathImage():String{
        return this.image;
    }

    fun getName():String{
        return this.name
    }




    override fun toString(): String {
        return "Category(id='$id', name='$name', pathImage='$image')"
    }
}

