package com.example.myapplication.viewmodels.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.modals.Order
import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.modals.OrderProductDetail
import com.example.myapplication.modals.Product
import com.example.myapplication.services.OrderProductService
import com.example.myapplication.services.ProductService
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class OrderProductViewModel: ViewModel() {
    private val _orderProduct = MutableLiveData<ArrayList<OrderProductDetail>>()

    val orderProduct: LiveData<ArrayList<OrderProductDetail>> = _orderProduct


    fun getAllProductOfOrder(orderId: Int){
        viewModelScope.launch(Dispatchers.IO){
            try{
                println("Here")
                val response = Utils.getRetrofit().create(OrderProductService::class.java).getAddProductOfOrder(orderId)
                _orderProduct.postValue(response)
                println(_orderProduct)

            }catch (e: Exception) {
                // handle error
            }
        }
    }
}