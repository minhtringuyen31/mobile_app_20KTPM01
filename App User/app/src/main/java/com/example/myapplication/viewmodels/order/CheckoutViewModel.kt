package com.example.myapplication.viewmodels.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.services.CheckoutService
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class CheckoutViewModel:ViewModel() {

    var subTotal:MutableLiveData<Double> = MutableLiveData<Double>(0.0);
    var total:MutableLiveData<Double> = MutableLiveData<Double>(0.0);
    private  var address:String="None"
    private var time:String="Current time"
    private var discount:String="None"
    private var promontionID:Int=-1;
    private var percentVoucher:Double=0.0;
    private var fromWhere = ""
    private val _newOrderProduct = MutableLiveData<OrderProduct>()
    val newOrderProduct: LiveData<OrderProduct> = _newOrderProduct
    private val retrofit: Retrofit = RetrofitClient.instance!!
    fun setpromontionID(id:String)
    {
        this.promontionID = id.toInt()
    }
    fun getPromotionID():Int{
        return this.promontionID
    }
    // Getter method
    // Subtotal getter and setter

    // Total getter and setter


    // Address getter and setter

    fun setFromPage(name:String){
        this.fromWhere= name;
    }
    fun getPage():String{
        return this.fromWhere
    }
    fun getAddress(): String {
        return address
    }

    fun setAddress(value: String) {
        address = value
    }

    // Time getter and setter
    fun getTime(): String {
        return time
    }

    fun setTime(value: String) {
        time = value
    }

    // Discount getter and setter
    fun getDiscount(): String {
        return this.discount
    }

    fun setDiscount(value: String) {
        this.discount = value
    }

    // Percent voucher getter and setter
    fun getPercentVoucher(): Double {
        return percentVoucher
    }

    fun setPercentVoucher(value: Double) {
        percentVoucher = value
    }

    fun createOrderProduct(orderProduct: OrderProduct) {
        viewModelScope.launch {
            try {
                val response = retrofit.create(CheckoutService::class.java).createOrderProduct(orderProduct);

                _newOrderProduct.postValue(response)
            } catch (e: Exception) {
                // handle error
            }
        }
    }

    override fun toString(): String {
        return "CheckoutViewModel(address='$address', time='$time', discount='$discount', percentVoucher=$percentVoucher, fromWhere='$fromWhere')"
    }


}