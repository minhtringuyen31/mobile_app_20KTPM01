package com.example.myapplication.services

import com.example.myapplication.modals.Order
import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.modals.Promotion
import retrofit2.http.*

interface CheckoutService {
    @GET("orderProducts/{id}")
    suspend fun getOrderProduct(@Path("id") orderProductID: Int): OrderProduct

    @GET("orderProducts/")
    suspend fun getAllOrderProduct(): ArrayList<OrderProduct>

    @POST("orderProducts/update/{id}")
    suspend fun updateOrderProduct(@Path("id") orderProductID: Int, @Body orderProduct: OrderProduct): OrderProduct

    @DELETE("orderProducts/delete/{id}")
    suspend fun deleteOrderProduct(@Path("id") orderProductID: Int): Int

    @POST("orderProducts/create/")
    suspend fun createOrderProduct(@Body orderProduct: OrderProduct): OrderProduct
}