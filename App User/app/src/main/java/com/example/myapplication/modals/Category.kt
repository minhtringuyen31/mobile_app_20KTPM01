package com.example.myapplication.modals

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.myapplication.MainActivity

class Category  {

    private var id:String = "";
    private var name:String ="";
    private var pathImage:String =""
    private var decodeImage: Bitmap? = null;
    fun setID(id:String){
        this.id=id;
    }
    fun setName(name:String){
        this.name=name;
    }
    fun setPathImage(path:String){
        this.pathImage=path;
    }
    constructor(id: String, name: String, pathImage: String) {
        this.id = id
        this.name = name
        this.pathImage = pathImage
    }
    fun getDecodeImage(): Bitmap? {
        try {
            val assetManager = MainActivity.appContext.resources.assets
            val inputStream = assetManager.open(this.getPathImage())
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
    fun getPathImage():String{
        return this.pathImage;
    }

    fun getName():String{
        return this.name
    }




    override fun toString(): String {
        return "Category(id='$id', name='$name', pathImage='$pathImage')"
    }
}

//import java.io.Serializable
//data class Category(
//    var name: String,
//    var image: Int,
//):Serializable

