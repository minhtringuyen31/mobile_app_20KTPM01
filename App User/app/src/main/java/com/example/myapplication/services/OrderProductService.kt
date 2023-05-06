package com.example.myapplication.services

import com.example.myapplication.modals.OrderProduct
import com.example.myapplication.modals.OrderProductDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface OrderProductService {
    @GET("orderProducts/{orderId}")
    suspend fun getAddProductOfOrder(@Path("orderId") orderId: Int): ArrayList<OrderProductDetail>

}