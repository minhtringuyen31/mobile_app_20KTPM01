package com.example.myapplication.viewmodels.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.services.CheckoutService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.launch

class CheckoutViewModel:ViewModel() {

    var subTotal:MutableLiveData<Double> = MutableLiveData<Double>(0.0);
    var total:MutableLiveData<Double> = MutableLiveData<Double>(0.0);
    private  var address:String="None"
    private var time:String="Current time"
    private var discount:String="None"
    private var percentVoucher:Double=0.0;
    private val _newOrderProduct = MutableLiveData<OrderProduct>()
    val newOrderProduct: LiveData<OrderProduct> = _newOrderProduct

    // Getter method
    // Subtotal getter and setter

    // Total getter and setter


    // Address getter and setter
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
        return discount
    }

    fun setDiscount(value: String) {
        discount = value
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
                val response = Utils.getRetrofit().create(CheckoutService::class.java).createOrderProduct(orderProduct);
                println("View: $response")
                _newOrderProduct.postValue(response)
            } catch (e: Exception) {
                // handle error
            }
        }
    }



}