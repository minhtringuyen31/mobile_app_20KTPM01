package com.example.myapplication.services

import retrofit2.http.Body
import retrofit2.http.POST

interface RefundOrder {
    @POST("orders/createRefund")
    suspend fun createRefund(@Body refund: com.example.myapplication.modals.RefundOrder): com.example.myapplication.modals.RefundOrder
    @POST("orders/findToken")
    suspend fun getRefund(@Body order_id: Int): com.example.myapplication.modals.RefundOrder

}