package com.example.myapplication.services

import com.example.myapplication.modals.Category
import retrofit2.http.*

interface CategoryService {
    @GET("categories/{id}")
    suspend fun getCategory(@Path("id") categoryId: Int): Category

    @GET("categories/")
    suspend fun getAllCategory(): ArrayList<Category>

    @POST("categories/update/{id}")
    suspend fun updateCategory(@Path("id") categoryId: Int, @Body category: Category): Category

    @DELETE("categories/delete/{id}")
    suspend fun deleteCategory(@Path("id") categoryId: Int):Int

    @POST("categories/create/")
    suspend fun createCategory(@Body category: Category): Category
}