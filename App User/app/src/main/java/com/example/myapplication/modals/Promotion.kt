package com.example.myapplication.modals

import android.os.Parcel
import android.os.Parcelable
import com.example.myapplication.pages.activities.promotion.PromotionData

class Promotion( private var id:Int,
                 private var name:String,
                 private  var description:String,
                 private var discount:Double,
                 private var image:String,
                 private var start_date:String,
                 private var end_date:String,
                 private var is_disable:Int,)  : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeDouble(discount)
        parcel.writeString(image)
        parcel.writeString(start_date)
        parcel.writeString(end_date)
        parcel.writeInt(is_disable)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PromotionData> {
        override fun createFromParcel(parcel: Parcel): PromotionData {
            return PromotionData(parcel)
        }

        override fun newArray(size: Int): Array<PromotionData?> {
            return arrayOfNulls(size)
        }
    }

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
        return this.start_date.take(10);
    }
    fun getEndDay():String{
        return this.end_date.take(10);
    }
    fun getDiscount():Double{
        return this.discount;
    }
    fun getDescription():String{
        return this.description
    }

    override fun toString(): String {
        return "Promotion(id=$id, name='$name', description='$description', discount=$discount, image='$image', start_date='$start_date', end_date='$end_date', is_disable=$is_disable)"
    }

}