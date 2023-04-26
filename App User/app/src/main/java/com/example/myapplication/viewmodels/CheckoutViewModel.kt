package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.services.CheckoutService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutViewModel:ViewModel() {
    private val _orderProduct = MutableLiveData<ArrayList<OrderProduct>>()
    val orderProduct: LiveData<ArrayList<OrderProduct>> = _orderProduct
    private val _product = MutableLiveData<OrderProduct>()
    val product: LiveData<OrderProduct> = _product


    private val _newOrderProduct = MutableLiveData<OrderProduct>()
    val newOrderProduct: LiveData<OrderProduct> = _newOrderProduct


    fun getAllOrderProduct() {
        // App 2 thread; MainThread(UI Thread) và Background Thread --> ANR
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(CheckoutService::class.java).getAllOrderProduct()
                _orderProduct.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
            } catch (e: Exception) {
                // handle error
            }
        }
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