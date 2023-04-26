package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Order
import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.services.CheckoutService
import com.example.myapplication.services.OrderService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel :ViewModel(){
    private val _orderProduct = MutableLiveData<ArrayList<Order>>()
    val orderProduct: LiveData<ArrayList<Order>> = _orderProduct


    fun getAllOrder() {
        // App 2 thread; MainThread(UI Thread) và Background Thread --> ANR
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java).getAllOrder()
                _orderProduct.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
                println(response)
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}