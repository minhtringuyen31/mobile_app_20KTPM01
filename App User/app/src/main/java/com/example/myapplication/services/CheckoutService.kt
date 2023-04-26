package com.example.myapplication.services

import com.example.myapplication.modals.Order
import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.modals.Promotion
import retrofit2.http.*

interface CheckoutService {
    @GET("orderProduct/{id}")
    suspend fun getOrderProduct(@Path("id") promotionId: Int): OrderProduct

    @GET("orderProduct/")
    suspend fun getAllOrderProduct(): ArrayList<OrderProduct>

    @POST("orderProduct/update/{id}")
    suspend fun updateOrderProduct(@Path("id") promotionId: Int, @Body promotion: OrderProduct): OrderProduct

    @DELETE("orderProduct/delete/{id}")
    suspend fun deleteOrderProduct(@Path("id") promotionId: Int): Int

    @POST("orderProduct/create/")
    suspend fun createOrderProduct(@Body promotion: OrderProduct): OrderProduct
}