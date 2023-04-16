package com.example.myapplication.services

import com.example.myapplication.modals.Topping
import retrofit2.http.*

interface ToppingService {
    @GET("toppings/{id}")
    suspend fun getTopping(@Path("id") toppingId: Int): Topping

    @GET("toppings/")
    suspend fun getAllTopping(): ArrayList<Topping>

    @POST("toppings/update/{id}")
    suspend fun updateTopping(@Path("id") toppingId: Int, @Body topping: Topping): Topping

    @DELETE("toppings/delete/{id}")
    suspend fun deleteTopping(@Path("id") toppingId: Int): Int

    @POST("toppings/create/")
    suspend fun createTopping(@Body topping: Topping): Topping
}