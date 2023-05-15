package com.example.myapplication.Admin.services

import com.example.myapplication.Admin.modals.Topping
import retrofit2.http.*

interface ToppingService {
    @GET("topping/{id}")
    suspend fun getTopping(@Path("id") toppingId: Int): Topping

    @GET("topping/")
    suspend fun getAllTopping(): List<Topping>

    @POST("topping/create")
    suspend fun createTopping(@Body topping: Topping): Topping

    @PUT("topping/update/{id}")
    suspend fun updateTopping(@Path("id") toppingId: Int, @Body topping: Topping): Topping

    @DELETE("topping/delete/{id}")
    suspend fun deleteTopping(@Path("id") toppingId: Int): Boolean
}