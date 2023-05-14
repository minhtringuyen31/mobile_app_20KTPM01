package com.example.appadmin.services

import retrofit2.Call
import retrofit2.http.*
import com.example.appadmin.modals.Category
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface CategoryService {
    @GET("category/{id}")
    suspend fun getCategory(@Path("id") categoryId: Int): Category

    @GET("category/")
    suspend fun getAllCategory(): List<Category>

    @Multipart
    @POST("category/create")
    suspend fun createCategory(
        @Part("name") name: RequestBody,
        @Part image: MultipartBody.Part
    ): Category

    @Multipart
    @PUT("category/update/{id}")
    suspend fun updateCategory(
        @Path("id") categoryId: Int,
        @Part("name") name: RequestBody,
        @Part image: MultipartBody.Part
    ): Category

    @PUT("category/updateWithoutImage/{id}")
    suspend fun updateCategoryWithoutImage(
        @Path("id") categoryId: Int,
        @Body category: Category
    ): Category

    @DELETE("category/delete/{id}")
    suspend fun deleteCategory(@Path("id") categoryId: Int): Boolean

    @PUT("category/disable/{id}")
    suspend fun disableCategory(@Path("id") categoryId: Int): Boolean

    @PUT("category/enable/{id}")
    suspend fun enableCategory(@Path("id") categoryId: Int): Boolean
}