package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Order

interface OrderService {
    @GET("order/{id}")
    fun getOrder(@Path("id") orderId: Int): Call<Order>

    @GET("order/")
    fun getAllOrder(): Call<List<Order>>

    @POST("order/create")
    fun createOrder(@Body order: Order): Call<Order>

    @PUT("order/update/{id}")
    fun updateOrder(@Path("id") orderId: Int, @Body order: Order): Call<Order>

    @DELETE("order/delete/{id}")
    fun deleteOrder(@Path("id") orderId: Int): Call<Boolean>
}