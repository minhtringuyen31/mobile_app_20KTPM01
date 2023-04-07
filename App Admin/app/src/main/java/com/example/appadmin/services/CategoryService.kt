package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Category

interface CategoryService {
    @GET("category/{id}")
    fun getCategory(@Path("id") categoryId: Int): Call<Category>

    @GET("category/")
    fun getAllCategory(): Call<List<Category>>

    @POST("category/create")
    fun createCategory(@Body category: Category): Call<Category>

    @PUT("category/update/{id}")
    fun updateCategory(@Path("id") categoryId: Int, @Body category: Category): Call<Category>

    @DELETE("category/delete/{id}")
    fun deleteCategory(@Path("id") categoryId: Int): Call<Boolean>
}