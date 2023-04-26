package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Order

interface OrderService {
    @GET("order/{id}")
    suspend fun getOrder(@Path("id") orderId: Int): Order

    @GET("order/")
    suspend fun getAllOrder(): List<Order>

    @POST("order/create")
    suspend fun createOrder(@Body order: Order): Order

    @PUT("order/update/{id}")
    suspend fun updateOrder(@Path("id") orderId: Int, @Body order: Order): Order

    @DELETE("order/delete/{id}")
    suspend fun deleteOrder(@Path("id") orderId: Int): Boolean

    @PUT("order/status/{id}")
    suspend fun updateStatus(@Path("id") orderId: Int, @Body status: String): Order
}