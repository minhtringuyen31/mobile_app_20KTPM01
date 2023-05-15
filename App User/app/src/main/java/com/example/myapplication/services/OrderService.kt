package com.example.myapplication.services

import com.example.myapplication.modals.Order
import retrofit2.http.*

interface OrderService {
    @GET("orders/{id}")
    suspend fun getOrder(@Path("id") orderID: Int): ArrayList<Order>

    @GET("orders/")
    suspend fun getAllOrder(): ArrayList<Order>

    @GET("ongoingorders/")
    suspend fun getAllOnGoingOrder(): ArrayList<Order>

    @GET("historyorders/")
    suspend fun getAllHistoryOrder(): ArrayList<Order>?

    @POST("orders/update/{id}")
    suspend fun updateOrder(@Path("id") orderID: Int, @Body promotion: Order): Order

    @DELETE("orders/delete/{id}")
    suspend fun deleteOrder(@Path("id") orderID: Int): Int
    @POST("orders/create/")
    suspend fun createOrder(@Body order: Order): Order

}