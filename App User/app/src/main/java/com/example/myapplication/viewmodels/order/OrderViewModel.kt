package com.example.myapplication.viewmodels.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.CartItem
import com.example.myapplication.modals.Order
import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.modals.RefundOrder
import com.example.myapplication.services.CheckoutService
import com.example.myapplication.services.OrderService
import com.example.myapplication.socket.SocketHandler
import com.example.myapplication.utils.Utils
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel :ViewModel(){
    private val _orderProduct = MutableLiveData<ArrayList<Order>>()
    val orderProduct: LiveData<ArrayList<Order>> = _orderProduct
    private val _newOrder = MutableLiveData<Order>()
    val order: LiveData<Order> = _newOrder


    private val _newOrder1 = MutableLiveData<RefundOrder>()
    val order1: LiveData<RefundOrder> = _newOrder1
    private val _newOrderProduct = MutableLiveData<OrderProduct>()
    val newOrderProduct: LiveData<OrderProduct> = _newOrderProduct
    fun getAllOrder(userId : Int ) {
        // App 2 thread; MainThread(UI Thread) và Background Thread --> ANR
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java).getOrder(userId)
                _orderProduct.postValue(response) // để đảm bảo rằng các giá trị được cập nhật trên luồng phụ (background thread).
                println(response)
//                println(_orderProduct)
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
    fun createRefund(orderProduct: RefundOrder) {
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(com.example.myapplication.services.RefundOrder::class.java).createRefund(orderProduct);
                println("View2222: $response")
                _newOrder1.postValue(response)
            } catch (e: Exception) {
                // handle error
            }
        }
    }


    fun createOrder(order: Order,cartItemCallAPI:ArrayList<CartItem>,transactionId:String) {

        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(OrderService::class.java).createOrder(order);
                _newOrder.postValue(response)
                val id =response.getId();

                var refundData = RefundOrder(id,transactionId)
                println(refundData)
                createRefund(refundData)
                var gson = Gson()
                var jsonString = gson.toJson(response)
                SocketHandler.setSocket()
                SocketHandler.establishConnection()
                SocketHandler.mSocket = SocketHandler.getSocket()
                SocketHandler.mSocket.emit("newOrder",jsonString)

                cartItemCallAPI.forEach {
                    val product_ID = it.getProductId();
                    val quantity = it.getQuantity();
                    val price  = it.getPrice();
                    val notes  = it.getNotes();
                    val topping= it.getTopping();
                    val size = it.getSize();
                    val orderProduct = OrderProduct(notes,id,product_ID,quantity,price,size,topping);
                    createOrderProduct(orderProduct);
                }
            } catch (e: Exception) {
                // handle error
            }
        }
    }
}