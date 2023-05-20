package com.example.myapplication.Admin.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Admin.modals.PaymentMethod
import com.example.myapplication.Admin.services.PaymentMethodService
import com.example.myapplication.Admin.utils.Utils
import kotlinx.coroutines.launch

class PaymentMethodController : ViewModel() {
    fun getPaymentMethod(id: Int): LiveData<PaymentMethod> {
        val _paymentMethod = MutableLiveData<PaymentMethod>()
        viewModelScope.launch {
            try {
                val response = Utils.getRetrofit().create(PaymentMethodService::class.java)
                    .getPaymentMethodById(id)
                _paymentMethod.value = response
            } catch (e: Exception) {
                // handle error
            }
        }
        return _paymentMethod
    }
}