package com.example.myapplication.services

import com.example.myapplication.modals.Cart
import com.example.myapplication.modals.CartItem
import retrofit2.http.*

interface CartItemService {
//    @GET("cartitems/{id}")
//    suspend fun getCartItem(@Path("id") cartItemId: Int): CartItem

    @GET("cartitems/")
    suspend fun getAllCartItem(): ArrayList<CartItem>

    @GET("cartitems/{userId}")
    suspend fun getCartItem(@Path("userId") userId: Int) : ArrayList<CartItem>

    @POST("cartitems/update/{id}")
    suspend fun updateCartItem(@Path("id") cartItemId: Int, @Body cartItem: CartItem): CartItem

    @DELETE("cartitems/delete/{id}")
    suspend fun deleteCartItem(@Path("id") cartItemId: Int):Int

    @POST("cartitems/create/")
    suspend fun createCartItem(@Body cartItem: CartItem): CartItem

    @DELETE("cartitems/removeAll/{id}")
    suspend fun removeAll(@Path("id") userID: Int): Int
}