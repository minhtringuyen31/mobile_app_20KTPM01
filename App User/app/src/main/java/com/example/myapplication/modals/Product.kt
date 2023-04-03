package com.example.myapplication.modals

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity

class Product:AppCompatActivity{
    private var id: String? = null
    private var name: String? = null
    private var description: String? = null
    private var price: Double? = null
    private var size: String? = null
    private var price_S: Double? = null
    private var price_M: Double? = null
    private var price_L: Double? = null
    private var note: String? = null
    private var image: String? = null
    private var status: Boolean? = null
    private var category_id: String? = null
    private var updatedAt: String? = null
    private var releaseDate: String? = null
    private var sales: Int? = 0

    constructor(
        id: String?,
        name: String?,
        description: String?,
        price: Double?,
        size: String?,
        price_S: Double?,
        price_M: Double?,
        price_L: Double?,
        note: String?,
        image: String?,
        status: Boolean?,
        category_id: String?,
        updatedAt: String?,
        releaseDate: String?,
        sales: Int?
    ) {
        this.id = id
        this.name = name
        this.description = description
        this.price = price
        this.size = size
        this.price_S = price_S
        this.price_M = price_M
        this.price_L = price_L
        this.note = note
        this.image = image
        this.status = status
        this.category_id = category_id
        this.updatedAt = updatedAt
        this.releaseDate = releaseDate
        this.sales = sales
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getDescription():String?
    {
        return description;
    }
    fun setDescription(description:String?)
    {
        this.description=description;
    }

    fun getPrice():Double?
    {
        return price;
    }

    fun setPrice(price :Double?)
    {
        this.price=price;
    }

    fun getSize():String?
    {
        return size;
    }

    fun setSize(size:String?)
    {
        this.size=size;
    }

    fun getPrice_S():Double?
    {
        return price_S;
    }

    fun setPrice_S(price_S :Double?)
    {
        this.price_S=price_S;
    }

    fun getPrice_M():Double?
    {
        return price_M;
    }

    fun setPrice_M(price_M :Double?)
    {
        this.price_M=price_M;
    }

    fun getPrice_L():Double?
    {
        return price_L;
    }

    fun setPrice_L(price_L :Double?)
    {
        this.price_L=price_L;
    }

    fun getNote():String?
    {
        return note;
    }

    fun setNote(note:String?)
    {
        this.note=note;
    }

    fun getImage():String?
    {
        return image;
    }

    fun setImage(image:String?)
    {
        this.image=image;
    }

    fun getStatus():Boolean?
    {
        return status;
    }

    fun setStatus(status:Boolean?)
    {
        this.status=status;
    }

    fun getCategory_id():String?
    {
        return category_id;
    }

    fun setCategory_id(category_id:String?)
    {
        this.category_id=category_id;
    }

    fun getUpdatedAt():String?
    {
        return updatedAt;
    }

    fun setUpdatedAt(updatedAt:String?)
    {
        this.updatedAt=updatedAt;
    }

    fun getReleaseDate():String?
    {
        return releaseDate;
    }

    fun setReleaseDate(releaseDate:String?)
    {
        this.releaseDate=releaseDate;
    }

    fun getSales():Int?
    {
        return sales;
    }

    fun setSales(sales:Int?)
    {
        this.sales=sales;
    }
    fun getDecodeImage(): Bitmap? {
        try {
            val assetManager =  MainActivity.appContext.resources.assets
            val inputStream = assetManager.open(this.getImage()!!)
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