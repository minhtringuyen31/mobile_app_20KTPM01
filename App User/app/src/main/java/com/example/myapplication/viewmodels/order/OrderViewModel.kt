package com.example.myapplication.viewmodels.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.CartItem
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
    private val _newOrder = MutableLiveData<Order>()
    val order: LiveData<Order> = _newOrder

    private val _newOrderProduct = MutableLiveData<OrderProduct>()
    val newOrderProduct: LiveData<OrderProduct> = _newOrderProduct
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

    fun getAllOnGoingOrder() {
        // App 2 thread; MainThread(UI Thread) và Background Thread --> ANR
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java).getAllOnGoingOrder()
                _orderProduct.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
                println(response)
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
    fun createOrder(order: Order,cartItemCallAPI:ArrayList<CartItem>) {
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java).createOrder(order);
                _newOrder.postValue(response)
                val id =response.getId();
                cartItemCallAPI.forEach {
                    val product_ID = it.getProductId();
                    val quantity = it.getQuantity();
                    val price  = it.getPrice();
                    val orderProduct = OrderProduct(id,"",price,product_ID,quantity);
                    println(orderProduct)
                    createOrderProduct(orderProduct);
                }
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}