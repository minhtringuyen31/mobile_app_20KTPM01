package com.example.myapplication.pages.activities.promotion
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate
import java.time.temporal.ChronoUnit


class PromotionData(
    private var id:Int,
    private var name:String,
    private  var description:String,
    private var discount:Double,
    private var image:String,
    private var start_date:String,
    private var end_date:String,
    private var is_disable:Int,
) : Parcelable {
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
    fun getID():Int{
        return this.id
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
    fun getDisable():Int{
        return this.is_disable;
    }

}