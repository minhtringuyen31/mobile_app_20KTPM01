package com.example.myapplication.Admin.services

import com.example.myapplication.Admin.modals.Order
import com.example.myapplication.Admin.modals.countOrder
import com.example.myapplication.Admin.modals.totalOrder
import retrofit2.http.*


interface OrderService {
    @GET("order/{id}")
    suspend fun getOrder(@Path("id") orderId: Int): Order

    @GET("order/")
    suspend fun getAllOrder(): List<Order>

    @GET("order/bydate")
    suspend fun getAllOrderByDate(): List<Order>
    @GET("order/byweek")
    suspend fun getAllOrderByWeek(): List<Order>
    @GET("order/bymonth")
    suspend fun getAllOrderByMonth(): List<Order>

    @POST("order/create")
    suspend fun createOrder(@Body order: Order): Order

    @PUT("order/update/{id}")
    suspend fun updateOrder(@Path("id") orderId: Int, @Body order: Order): Order

    @DELETE("order/delete/{id}")
    suspend fun deleteOrder(@Path("id") orderId: Int): Boolean

    @PUT("order/deny/{id}")
    suspend fun changeDenyStatus(@Path("id") orderId: Int): Boolean

    @PUT("order/delivered/{id}")
    suspend fun changeDeliveredStatus(@Path("id") orderId: Int): Boolean

    @PUT("order/accept/{id}")
    suspend fun changeAcceptStatus(@Path("id") orderId: Int): Boolean

    @GET("order/count")
    suspend fun countOrder(): countOrder

    @GET("order/total")
    suspend fun totalOrder(): totalOrder
}