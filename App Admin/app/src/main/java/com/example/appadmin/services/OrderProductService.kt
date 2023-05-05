package com.example.appadmin.services

import com.example.appadmin.modals.OrderProduct
import retrofit2.http.*

interface OrderProductService {
    @GET("order_product/")
    suspend fun getAllOrderProduct(): List<OrderProduct>

    @GET("order_product/{id}")
    suspend fun getOrderProductById(@Path("id") id: Int): OrderProduct

    @POST("order_product/create")
    suspend fun createOrderProduct(@Body orderProduct: OrderProduct): OrderProduct

    @PUT("order_product/update/{id}")
    suspend fun updateOrderProduct(
        @Path("id") id: Int,
        @Body orderProduct: OrderProduct
    ): OrderProduct

    @DELETE("order_product/delete/{id}")
    suspend fun deleteOrderProduct(@Path("id") id: Int): Boolean

    @GET("order_product/order/{id}")
    suspend fun getOrderProductByOrderId(@Path("id") id: Int): List<OrderProduct>
}