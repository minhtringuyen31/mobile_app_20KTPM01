package com.example.appadmin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appadmin.modals.Order
import com.example.appadmin.services.OrderService
import com.example.appadmin.utils.Utils
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrderController : ViewModel() {
    fun getOrder(id: Int): LiveData<Order> {
        val _order = MutableLiveData<Order>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java).getOrder(id)
                _order.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _order
    }

    fun getAllOrder(): LiveData<List<Order>> {
        val _orders = MutableLiveData<List<Order>>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(OrderService::class.java).getAllOrder()
                _orders.value = response
                println(response[0].getId())
            } catch (e: Exception) {
                // handle error
            }
        }
        return _orders
    }

    fun createOrder(orderNew: Order): LiveData<Order> {
        val _order = MutableLiveData<Order>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(OrderService::class.java).createOrder(orderNew)
                _order.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _order
    }

    fun updateOrder(id: Int, orderNew: Order): LiveData<Order> {
        val _order = MutableLiveData<Order>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java)
                    .updateOrder(id, orderNew)
                _order.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _order
    }

    fun deleteOrder(id: Int): LiveData<Boolean> {
        val _isDeleted = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java).deleteOrder(id)
                _isDeleted.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _isDeleted
    }

    fun updateStatus(id: Int, status: Int): LiveData<Order> {
        val _order = MutableLiveData<Order>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(OrderService::class.java).updateStatus(id, status)
                _order.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _order
    }
}