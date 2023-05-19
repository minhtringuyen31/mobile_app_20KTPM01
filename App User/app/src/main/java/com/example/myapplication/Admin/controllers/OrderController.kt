package com.example.myapplication.Admin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Admin.modals.Order
import com.example.myapplication.Admin.modals.countOrder
import com.example.myapplication.Admin.modals.totalOrder
import com.example.myapplication.Admin.services.OrderService
import com.example.myapplication.Admin.utils.Utils
import kotlinx.coroutines.launch

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

    fun changeAcceptStatus(id: Int): LiveData<Boolean> {
        val _isChanged = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java)
                    .changeAcceptStatus(id)
                _isChanged.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _isChanged
    }

    fun changeDeliveredStatus(id: Int): LiveData<Boolean> {
        val _isChanged = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java)
                    .changeDeliveredStatus(id)
                _isChanged.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _isChanged
    }

    fun changeDenyStatus(id: Int): LiveData<Boolean> {
        val _isChanged = MutableLiveData<Boolean>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java)
                    .changeDenyStatus(id)
                _isChanged.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _isChanged
    }

    fun countOrder(): LiveData<countOrder> {
        val _count = MutableLiveData<countOrder>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java).countOrder()
                _count.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _count
    }

    fun totalOrder(): LiveData<totalOrder> {
        val _total = MutableLiveData<totalOrder>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java).totalOrder()
                _total.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _total
    }
}