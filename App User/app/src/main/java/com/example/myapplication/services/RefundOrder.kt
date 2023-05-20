package com.example.myapplication.services

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface RefundOrder {
    @POST("orders/createRefund")
    suspend fun createRefund(@Body refund: com.example.myapplication.modals.RefundOrder): com.example.myapplication.modals.RefundOrder
    @POST("orders/findToken/{id}")
    suspend fun getRefund(@Path("id") id: Int): com.example.myapplication.modals.RefundOrder

}