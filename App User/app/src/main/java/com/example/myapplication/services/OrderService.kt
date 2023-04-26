package com.example.myapplication.services

import com.example.myapplication.modals.Order
import retrofit2.http.*

interface OrderService {
    @GET("orders/{id}")
    suspend fun getOrder(@Path("id") promotionId: Int): Order

    @GET("orders/")
    suspend fun getAllOrder(): ArrayList<Order>

    @POST("orders/update/{id}")
    suspend fun updateOrder(@Path("id") promotionId: Int, @Body promotion: Order): Order

    @DELETE("orders/delete/{id}")
    suspend fun deleteOrder(@Path("id") promotionId: Int): Int
    @POST("orders/create/")
    suspend fun createOrder(@Body promotion: Order): Order
}