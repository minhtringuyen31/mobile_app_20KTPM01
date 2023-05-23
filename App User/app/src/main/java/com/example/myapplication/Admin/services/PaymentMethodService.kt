package com.example.myapplication.Admin.services

import com.example.myapplication.Admin.modals.PaymentMethod
import retrofit2.http.*

interface PaymentMethodService {
    @GET("payment/{id}")
    suspend fun getPaymentMethodById(@Path("id") id: Int): PaymentMethod
}