package com.example.myapplication.viewmodels.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.RefundOrder
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class RefundViewModel:ViewModel() {
    private val _newOrder = MutableLiveData<RefundOrder>()
    val order: LiveData<RefundOrder> = _newOrder
    private val _Order = MutableLiveData<RefundOrder>()
    private val retrofit: Retrofit = RetrofitClient.instance!!
    val getorder: LiveData<RefundOrder> = _Order
    fun createRefund(orderProduct: RefundOrder) {
        viewModelScope.launch {
            try {
                val response = retrofit.create(com.example.myapplication.services.RefundOrder::class.java).createRefund(orderProduct);

                _newOrder.postValue(response)
            } catch (e: Exception) {
                // handle error
            }
        }
    }
    fun getTokenByOrderID(orderID: Int) {
        viewModelScope.launch {
            try {
                val response = retrofit.create(com.example.myapplication.services.RefundOrder::class.java).getRefund(orderID);
                println("ViewGetTOken: $response")
                _Order.postValue(response)
            } catch (e: Exception) {
                // handle error
            }
        }
    }

}