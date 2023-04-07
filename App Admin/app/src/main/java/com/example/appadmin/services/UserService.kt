package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.User

interface UserService {
    @GET("user/{id}")
    fun getUser(@Path("id") userId: Int): Call<User>

    @GET("user/")
    fun getAllUser(): Call<List<User>>

    @POST("user/create")
    fun createUser(@Body user: User): Call<User>

    @PUT("user/update/{id}")
    fun updateUser(@Path("id") userId: Int, @Body user: User): Call<User>

    @DELETE("user/delete/{id}")
    fun deleteUser(@Path("id") userId: Int): Call<Boolean>


}