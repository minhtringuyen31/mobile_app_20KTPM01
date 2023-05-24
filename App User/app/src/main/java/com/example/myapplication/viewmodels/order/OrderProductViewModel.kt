package com.example.myapplication.viewmodels.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.OrderProductDetail
import com.example.myapplication.services.OrderProductService
import com.example.myapplication.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class OrderProductViewModel: ViewModel() {
    private val _orderProduct = MutableLiveData<ArrayList<OrderProductDetail>>()
    private val retrofit: Retrofit = RetrofitClient.instance!!
    val orderProduct: LiveData<ArrayList<OrderProductDetail>> = _orderProduct


    fun getAllProductOfOrder(orderId: Int){
        viewModelScope.launch(Dispatchers.IO){
            try{

                val response = retrofit.create(OrderProductService::class.java).getAddProductOfOrder(orderId)
                _orderProduct.postValue(response)
                println(_orderProduct)

            }catch (e: Exception) {
                // handle error
            }
        }
    }
}