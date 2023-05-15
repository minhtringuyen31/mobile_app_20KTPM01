package com.example.myapplication.Admin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Admin.modals.OrderProduct
import com.example.myapplication.Admin.services.OrderProductService
import com.example.myapplication.Admin.utils.Utils
import kotlinx.coroutines.launch


class OrderProductController : ViewModel() {
    fun getOrderProduct(id: Int): LiveData<OrderProduct> {
        val _orderProduct = MutableLiveData<OrderProduct>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderProductService::class.java)
                    .getOrderProductById(id)
                _orderProduct.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _orderProduct
    }

    fun getAllOrderProduct(): LiveData<List<OrderProduct>> {
        val _orderProducts = MutableLiveData<List<OrderProduct>>()
        viewModelScope.launch {
            try {
                val response =
                    Utils.getRetrofit().create(OrderProductService::class.java).getAllOrderProduct()
                _orderProducts.value = response
                println(response[0].getId())
            } catch (e: Exception) {
                // handle error
            }
        }
        return _orderProducts
    }

    fun createOrderProduct(orderProductNew: OrderProduct): LiveData<OrderProduct> {
        val _orderProduct = MutableLiveData<OrderProduct>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderProductService::class.java)
                    .createOrderProduct(orderProductNew)
                _orderProduct.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _orderProduct
    }

    fun updateOrderProduct(id: Int, orderProductNew: OrderProduct): LiveData<OrderProduct> {
        val _orderProduct = MutableLiveData<OrderProduct>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderProductService::class.java)
                    .updateOrderProduct(id, orderProductNew)
                _orderProduct.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _orderProduct
    }

    fun deleteOrderProduct(id: Int): LiveData<Boolean> {
        val _orderProduct = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderProductService::class.java)
                    .deleteOrderProduct(id)
                _orderProduct.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _orderProduct
    }

    fun getOrderProductByOrderId(id: Int): LiveData<List<OrderProduct>> {
        val _orderProducts = MutableLiveData<List<OrderProduct>>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderProductService::class.java)
                    .getOrderProductByOrderId(id)
                _orderProducts.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _orderProducts
    }
}