package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Category

interface CategoryService {
    @GET("category/{id}")
    suspend fun getCategory(@Path("id") categoryId: Int): Category

    @GET("category/")
    suspend fun getAllCategory(): List<Category>

    @POST("category/create")
    suspend fun createCategory(@Body category: Category): Category

    @PUT("category/update/{id}")
    suspend fun updateCategory(@Path("id") categoryId: Int, @Body category: Category): Category

    @DELETE("category/delete/{id}")
    suspend fun deleteCategory(@Path("id") categoryId: Int): Boolean

    @PUT("category/disable/{id}")
    suspend fun disableCategory(@Path("id") categoryId: Int): Boolean

    @PUT("category/enable/{id}")
    suspend fun enableCategory(@Path("id") categoryId: Int): Boolean
}